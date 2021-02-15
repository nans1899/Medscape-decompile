package org.mockito.internal.exceptions;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.apache.commons.io.IOUtils;
import org.mockito.exceptions.base.MockitoAssertionError;
import org.mockito.exceptions.base.MockitoException;
import org.mockito.exceptions.misusing.CannotStubVoidMethodWithReturnValue;
import org.mockito.exceptions.misusing.CannotVerifyStubOnlyMock;
import org.mockito.exceptions.misusing.FriendlyReminderException;
import org.mockito.exceptions.misusing.InjectMocksException;
import org.mockito.exceptions.misusing.InvalidUseOfMatchersException;
import org.mockito.exceptions.misusing.MissingMethodInvocationException;
import org.mockito.exceptions.misusing.NotAMockException;
import org.mockito.exceptions.misusing.NullInsteadOfMockException;
import org.mockito.exceptions.misusing.PotentialStubbingProblem;
import org.mockito.exceptions.misusing.RedundantListenerException;
import org.mockito.exceptions.misusing.UnfinishedMockingSessionException;
import org.mockito.exceptions.misusing.UnfinishedStubbingException;
import org.mockito.exceptions.misusing.UnfinishedVerificationException;
import org.mockito.exceptions.misusing.UnnecessaryStubbingException;
import org.mockito.exceptions.misusing.WrongTypeOfReturnValue;
import org.mockito.exceptions.verification.MoreThanAllowedActualInvocations;
import org.mockito.exceptions.verification.NeverWantedButInvoked;
import org.mockito.exceptions.verification.NoInteractionsWanted;
import org.mockito.exceptions.verification.SmartNullPointerException;
import org.mockito.exceptions.verification.TooLittleActualInvocations;
import org.mockito.exceptions.verification.TooManyActualInvocations;
import org.mockito.exceptions.verification.VerificationInOrderFailure;
import org.mockito.exceptions.verification.WantedButNotInvoked;
import org.mockito.internal.debugging.LocationImpl;
import org.mockito.internal.exceptions.util.ScenarioPrinter;
import org.mockito.internal.junit.ExceptionFactory;
import org.mockito.internal.matchers.LocalizedMatcher;
import org.mockito.internal.reporting.Discrepancy;
import org.mockito.internal.reporting.Pluralizer;
import org.mockito.internal.util.MockUtil;
import org.mockito.internal.util.StringUtil;
import org.mockito.invocation.DescribedInvocation;
import org.mockito.invocation.Invocation;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.invocation.Location;
import org.mockito.listeners.InvocationListener;
import org.mockito.mock.SerializableMode;

public class Reporter {
    private static final String NON_PUBLIC_PARENT = "Mocking methods declared on non-public parent classes is not supported.";

    private Reporter() {
    }

    public static MockitoException checkedExceptionInvalid(Throwable th) {
        return new MockitoException(StringUtil.join("Checked exception is invalid for this method!", "Invalid: " + th));
    }

    public static MockitoException cannotStubWithNullThrowable() {
        return new MockitoException(StringUtil.join("Cannot stub with null throwable!"));
    }

    public static MockitoException unfinishedStubbing(Location location) {
        return new UnfinishedStubbingException(StringUtil.join("Unfinished stubbing detected here:", location, "", "E.g. thenReturn() may be missing.", "Examples of correct stubbing:", "    when(mock.isOk()).thenReturn(true);", "    when(mock.isOk()).thenThrow(exception);", "    doThrow(exception).when(mock).someVoidMethod();", "Hints:", " 1. missing thenReturn()", " 2. you are trying to stub a final method, which is not supported", " 3: you are stubbing the behaviour of another mock inside before 'thenReturn' instruction is completed", ""));
    }

    public static MockitoException incorrectUseOfApi() {
        return new MockitoException(StringUtil.join("Incorrect use of API detected here:", new LocationImpl(), "", "You probably stored a reference to OngoingStubbing returned by when() and called stubbing methods like thenReturn() on this reference more than once.", "Examples of correct usage:", "    when(mock.isOk()).thenReturn(true).thenReturn(false).thenThrow(exception);", "    when(mock.isOk()).thenReturn(true, false).thenThrow(exception);", ""));
    }

    public static MockitoException missingMethodInvocation() {
        return new MissingMethodInvocationException(StringUtil.join("when() requires an argument which has to be 'a method call on a mock'.", "For example:", "    when(mock.getArticles()).thenReturn(articles);", "", "Also, this error might show up because:", "1. you stub either of: final/private/equals()/hashCode() methods.", "   Those methods *cannot* be stubbed/verified.", "   Mocking methods declared on non-public parent classes is not supported.", "2. inside when() you don't call method on mock but on some other object.", ""));
    }

    public static MockitoException unfinishedVerificationException(Location location) {
        return new UnfinishedVerificationException(StringUtil.join("Missing method call for verify(mock) here:", location, "", "Example of correct verification:", "    verify(mock).doSomething()", "", "Also, this error might show up because you verify either of: final/private/equals()/hashCode() methods.", "Those methods *cannot* be stubbed/verified.", NON_PUBLIC_PARENT, ""));
    }

    public static MockitoException notAMockPassedToVerify(Class<?> cls) {
        return new NotAMockException(StringUtil.join("Argument passed to verify() is of type " + cls.getSimpleName() + " and is not a mock!", "Make sure you place the parenthesis correctly!", "See the examples of correct verifications:", "    verify(mock).someMethod();", "    verify(mock, times(10)).someMethod();", "    verify(mock, atLeastOnce()).someMethod();"));
    }

    public static MockitoException nullPassedToVerify() {
        return new NullInsteadOfMockException(StringUtil.join("Argument passed to verify() should be a mock but is null!", "Examples of correct verifications:", "    verify(mock).someMethod();", "    verify(mock, times(10)).someMethod();", "    verify(mock, atLeastOnce()).someMethod();", "    not: verify(mock.someMethod());", "Also, if you use @Mock annotation don't miss initMocks()"));
    }

    public static MockitoException notAMockPassedToWhenMethod() {
        return new NotAMockException(StringUtil.join("Argument passed to when() is not a mock!", "Example of correct stubbing:", "    doThrow(new RuntimeException()).when(mock).someMethod();"));
    }

    public static MockitoException nullPassedToWhenMethod() {
        return new NullInsteadOfMockException(StringUtil.join("Argument passed to when() is null!", "Example of correct stubbing:", "    doThrow(new RuntimeException()).when(mock).someMethod();", "Also, if you use @Mock annotation don't miss initMocks()"));
    }

    public static MockitoException mocksHaveToBePassedToVerifyNoMoreInteractions() {
        return new MockitoException(StringUtil.join("Method requires argument(s)!", "Pass mocks that should be verified, e.g:", "    verifyNoMoreInteractions(mockOne, mockTwo);", "    verifyZeroInteractions(mockOne, mockTwo);", ""));
    }

    public static MockitoException notAMockPassedToVerifyNoMoreInteractions() {
        return new NotAMockException(StringUtil.join("Argument(s) passed is not a mock!", "Examples of correct verifications:", "    verifyNoMoreInteractions(mockOne, mockTwo);", "    verifyZeroInteractions(mockOne, mockTwo);", ""));
    }

    public static MockitoException nullPassedToVerifyNoMoreInteractions() {
        return new NullInsteadOfMockException(StringUtil.join("Argument(s) passed is null!", "Examples of correct verifications:", "    verifyNoMoreInteractions(mockOne, mockTwo);", "    verifyZeroInteractions(mockOne, mockTwo);"));
    }

    public static MockitoException notAMockPassedWhenCreatingInOrder() {
        return new NotAMockException(StringUtil.join("Argument(s) passed is not a mock!", "Pass mocks that require verification in order.", "For example:", "    InOrder inOrder = inOrder(mockOne, mockTwo);"));
    }

    public static MockitoException nullPassedWhenCreatingInOrder() {
        return new NullInsteadOfMockException(StringUtil.join("Argument(s) passed is null!", "Pass mocks that require verification in order.", "For example:", "    InOrder inOrder = inOrder(mockOne, mockTwo);"));
    }

    public static MockitoException mocksHaveToBePassedWhenCreatingInOrder() {
        return new MockitoException(StringUtil.join("Method requires argument(s)!", "Pass mocks that require verification in order.", "For example:", "    InOrder inOrder = inOrder(mockOne, mockTwo);"));
    }

    public static MockitoException inOrderRequiresFamiliarMock() {
        return new MockitoException(StringUtil.join("InOrder can only verify mocks that were passed in during creation of InOrder.", "For example:", "    InOrder inOrder = inOrder(mockOne);", "    inOrder.verify(mockOne).doStuff();"));
    }

    public static MockitoException invalidUseOfMatchers(int i, List<LocalizedMatcher> list) {
        return new InvalidUseOfMatchersException(StringUtil.join("Invalid use of argument matchers!", i + " matchers expected, " + list.size() + " recorded:" + locationsOf(list), "", "This exception may occur if matchers are combined with raw values:", "    //incorrect:", "    someMethod(anyObject(), \"raw String\");", "When using matchers, all arguments have to be provided by matchers.", "For example:", "    //correct:", "    someMethod(anyObject(), eq(\"String by matcher\"));", "", "For more info see javadoc for Matchers class.", ""));
    }

    public static MockitoException incorrectUseOfAdditionalMatchers(String str, int i, Collection<LocalizedMatcher> collection) {
        return new InvalidUseOfMatchersException(StringUtil.join("Invalid use of argument matchers inside additional matcher " + str + " !", new LocationImpl(), "", i + " sub matchers expected, " + collection.size() + " recorded:", locationsOf(collection), "", "This exception may occur if matchers are combined with raw values:", "    //incorrect:", "    someMethod(AdditionalMatchers.and(isNotNull(), \"raw String\");", "When using matchers, all arguments have to be provided by matchers.", "For example:", "    //correct:", "    someMethod(AdditionalMatchers.and(isNotNull(), eq(\"raw String\"));", "", "For more info see javadoc for Matchers and AdditionalMatchers classes.", ""));
    }

    public static MockitoException stubPassedToVerify(Object obj) {
        return new CannotVerifyStubOnlyMock(StringUtil.join("Argument \"" + MockUtil.getMockName(obj) + "\" passed to verify is a stubOnly() mock which cannot be verified.", "If you intend to verify invocations on this mock, don't use stubOnly() in its MockSettings."));
    }

    public static MockitoException reportNoSubMatchersFound(String str) {
        return new InvalidUseOfMatchersException(StringUtil.join("No matchers found for additional matcher " + str, new LocationImpl(), ""));
    }

    private static Object locationsOf(Collection<LocalizedMatcher> collection) {
        ArrayList arrayList = new ArrayList();
        for (LocalizedMatcher location : collection) {
            arrayList.add(location.getLocation().toString());
        }
        return StringUtil.join(arrayList.toArray());
    }

    public static AssertionError argumentsAreDifferent(String str, String str2, Location location) {
        return ExceptionFactory.createArgumentsAreDifferentException(StringUtil.join("Argument(s) are different! Wanted:", str, new LocationImpl(), "Actual invocation has different arguments:", str2, location, ""), str, str2);
    }

    public static MockitoAssertionError wantedButNotInvoked(DescribedInvocation describedInvocation) {
        return new WantedButNotInvoked(createWantedButNotInvokedMessage(describedInvocation));
    }

    public static MockitoAssertionError wantedButNotInvoked(DescribedInvocation describedInvocation, List<? extends DescribedInvocation> list) {
        String str;
        if (list.isEmpty()) {
            str = "Actually, there were zero interactions with this mock.\n";
        } else {
            StringBuilder sb = new StringBuilder("\nHowever, there " + Pluralizer.were_exactly_x_interactions(list.size()) + " with this mock:\n");
            for (DescribedInvocation describedInvocation2 : list) {
                sb.append(describedInvocation2.toString());
                sb.append(IOUtils.LINE_SEPARATOR_UNIX);
                sb.append(describedInvocation2.getLocation());
                sb.append("\n\n");
            }
            str = sb.toString();
        }
        String createWantedButNotInvokedMessage = createWantedButNotInvokedMessage(describedInvocation);
        return new WantedButNotInvoked(createWantedButNotInvokedMessage + str);
    }

    private static String createWantedButNotInvokedMessage(DescribedInvocation describedInvocation) {
        return StringUtil.join("Wanted but not invoked:", describedInvocation.toString(), new LocationImpl(), "");
    }

    public static MockitoAssertionError wantedButNotInvokedInOrder(DescribedInvocation describedInvocation, DescribedInvocation describedInvocation2) {
        return new VerificationInOrderFailure(StringUtil.join("Verification in order failure", "Wanted but not invoked:", describedInvocation.toString(), new LocationImpl(), "Wanted anywhere AFTER following interaction:", describedInvocation2.toString(), describedInvocation2.getLocation(), ""));
    }

    public static MockitoAssertionError tooManyActualInvocations(int i, int i2, DescribedInvocation describedInvocation, List<Location> list) {
        return new TooManyActualInvocations(createTooManyInvocationsMessage(i, i2, describedInvocation, list));
    }

    private static String createTooManyInvocationsMessage(int i, int i2, DescribedInvocation describedInvocation, List<Location> list) {
        return StringUtil.join(describedInvocation.toString(), "Wanted " + Pluralizer.pluralize(i) + ":", new LocationImpl(), "But was " + Pluralizer.pluralize(i2) + ":", createAllLocationsMessage(list), "");
    }

    public static MockitoAssertionError neverWantedButInvoked(DescribedInvocation describedInvocation, List<Location> list) {
        return new NeverWantedButInvoked(StringUtil.join(describedInvocation.toString(), "Never wanted here:", new LocationImpl(), "But invoked here:", createAllLocationsMessage(list)));
    }

    public static MockitoAssertionError tooManyActualInvocationsInOrder(int i, int i2, DescribedInvocation describedInvocation, List<Location> list) {
        String createTooManyInvocationsMessage = createTooManyInvocationsMessage(i, i2, describedInvocation, list);
        return new VerificationInOrderFailure(StringUtil.join("Verification in order failure:" + createTooManyInvocationsMessage));
    }

    private static String createAllLocationsMessage(List<Location> list) {
        if (list == null) {
            return IOUtils.LINE_SEPARATOR_UNIX;
        }
        StringBuilder sb = new StringBuilder();
        for (Location append : list) {
            sb.append(append);
            sb.append(IOUtils.LINE_SEPARATOR_UNIX);
        }
        return sb.toString();
    }

    private static String createTooLittleInvocationsMessage(Discrepancy discrepancy, DescribedInvocation describedInvocation, List<Location> list) {
        Object[] objArr = new Object[5];
        objArr[0] = describedInvocation.toString();
        StringBuilder sb = new StringBuilder();
        sb.append("Wanted ");
        sb.append(discrepancy.getPluralizedWantedCount());
        String str = ".";
        sb.append(discrepancy.getWantedCount() == 0 ? str : ":");
        objArr[1] = sb.toString();
        objArr[2] = new LocationImpl();
        StringBuilder sb2 = new StringBuilder();
        sb2.append("But was ");
        sb2.append(discrepancy.getPluralizedActualCount());
        if (discrepancy.getActualCount() != 0) {
            str = ":";
        }
        sb2.append(str);
        objArr[3] = sb2.toString();
        objArr[4] = createAllLocationsMessage(list);
        return StringUtil.join(objArr);
    }

    public static MockitoAssertionError tooLittleActualInvocations(Discrepancy discrepancy, DescribedInvocation describedInvocation, List<Location> list) {
        return new TooLittleActualInvocations(createTooLittleInvocationsMessage(discrepancy, describedInvocation, list));
    }

    public static MockitoAssertionError tooLittleActualInvocationsInOrder(Discrepancy discrepancy, DescribedInvocation describedInvocation, List<Location> list) {
        String createTooLittleInvocationsMessage = createTooLittleInvocationsMessage(discrepancy, describedInvocation, list);
        return new VerificationInOrderFailure(StringUtil.join("Verification in order failure:" + createTooLittleInvocationsMessage));
    }

    public static MockitoAssertionError noMoreInteractionsWanted(Invocation invocation, List<VerificationAwareInvocation> list) {
        String print = new ScenarioPrinter().print(list);
        return new NoInteractionsWanted(StringUtil.join("No interactions wanted here:", new LocationImpl(), "But found this interaction on mock '" + MockUtil.getMockName(invocation.getMock()) + "':", invocation.getLocation(), print));
    }

    public static MockitoAssertionError noMoreInteractionsWantedInOrder(Invocation invocation) {
        return new VerificationInOrderFailure(StringUtil.join("No interactions wanted here:", new LocationImpl(), "But found this interaction on mock '" + MockUtil.getMockName(invocation.getMock()) + "':", invocation.getLocation()));
    }

    public static MockitoException cannotMockClass(Class<?> cls, String str) {
        return new MockitoException(StringUtil.join("Cannot mock/spy " + cls.toString(), "Mockito cannot mock/spy because :", " - " + str));
    }

    public static MockitoException cannotStubVoidMethodWithAReturnValue(String str) {
        return new CannotStubVoidMethodWithReturnValue(StringUtil.join("'" + str + "' is a *void method* and it *cannot* be stubbed with a *return value*!", "Voids are usually stubbed with Throwables:", "    doThrow(exception).when(mock).someVoidMethod();", "If you need to set the void method to do nothing you can use:", "    doNothing().when(mock).someVoidMethod();", "For more information, check out the javadocs for Mockito.doNothing().", "***", "If you're unsure why you're getting above error read on.", "Due to the nature of the syntax above problem might occur because:", "1. The method you are trying to stub is *overloaded*. Make sure you are calling the right overloaded version.", "2. Somewhere in your test you are stubbing *final methods*. Sorry, Mockito does not verify/stub final methods.", "3. A spy is stubbed using when(spy.foo()).then() syntax. It is safer to stub spies - ", "   - with doReturn|Throw() family of methods. More in javadocs for Mockito.spy() method.", "4. Mocking methods declared on non-public parent classes is not supported.", ""));
    }

    public static MockitoException onlyVoidMethodsCanBeSetToDoNothing() {
        return new MockitoException(StringUtil.join("Only void methods can doNothing()!", "Example of correct use of doNothing():", "    doNothing().", "    doThrow(new RuntimeException())", "    .when(mock).someVoidMethod();", "Above means:", "someVoidMethod() does nothing the 1st time but throws an exception the 2nd time is called"));
    }

    public static MockitoException wrongTypeOfReturnValue(String str, String str2, String str3) {
        return new WrongTypeOfReturnValue(StringUtil.join(str2 + " cannot be returned by " + str3 + "()", str3 + "() should return " + str, "***", "If you're unsure why you're getting above error read on.", "Due to the nature of the syntax above problem might occur because:", "1. This exception *might* occur in wrongly written multi-threaded tests.", "   Please refer to Mockito FAQ on limitations of concurrency testing.", "2. A spy is stubbed using when(spy.foo()).then() syntax. It is safer to stub spies - ", "   - with doReturn|Throw() family of methods. More in javadocs for Mockito.spy() method.", ""));
    }

    public static MockitoException wrongTypeReturnedByDefaultAnswer(Object obj, String str, String str2, String str3) {
        return new WrongTypeOfReturnValue(StringUtil.join("Default answer returned a result with the wrong type:", str2 + " cannot be returned by " + str3 + "()", str3 + "() should return " + str, "", "The default answer of " + MockUtil.getMockName(obj) + " that was configured on the mock is probably incorrectly implemented.", ""));
    }

    public static MoreThanAllowedActualInvocations wantedAtMostX(int i, int i2) {
        return new MoreThanAllowedActualInvocations(StringUtil.join("Wanted at most " + Pluralizer.pluralize(i) + " but was " + i2));
    }

    public static MockitoException misplacedArgumentMatcher(List<LocalizedMatcher> list) {
        return new InvalidUseOfMatchersException(StringUtil.join("Misplaced or misused argument matcher detected here:", locationsOf(list), "", "You cannot use argument matchers outside of verification or stubbing.", "Examples of correct usage of argument matchers:", "    when(mock.get(anyInt())).thenReturn(null);", "    doThrow(new RuntimeException()).when(mock).someVoidMethod(anyObject());", "    verify(mock).someMethod(contains(\"foo\"))", "", "This message may appear after an NullPointerException if the last matcher is returning an object ", "like any() but the stubbed method signature expect a primitive argument, in this case,", "use primitive alternatives.", "    when(mock.get(any())); // bad use, will raise NPE", "    when(mock.get(anyInt())); // correct usage use", "", "Also, this error might show up because you use argument matchers with methods that cannot be mocked.", "Following methods *cannot* be stubbed/verified: final/private/equals()/hashCode().", NON_PUBLIC_PARENT, ""));
    }

    public static MockitoException smartNullPointerException(String str, Location location) {
        return new SmartNullPointerException(StringUtil.join("You have a NullPointerException here:", new LocationImpl(), "because this method call was *not* stubbed correctly:", location, str, ""));
    }

    public static MockitoException noArgumentValueWasCaptured() {
        return new MockitoException(StringUtil.join("No argument value was captured!", "You might have forgotten to use argument.capture() in verify()...", "...or you used capture() in stubbing but stubbed method was not called.", "Be aware that it is recommended to use capture() only with verify()", "", "Examples of correct argument capturing:", "    ArgumentCaptor<Person> argument = ArgumentCaptor.forClass(Person.class);", "    verify(mock).doSomething(argument.capture());", "    assertEquals(\"John\", argument.getValue().getName());", ""));
    }

    public static MockitoException extraInterfacesDoesNotAcceptNullParameters() {
        return new MockitoException(StringUtil.join("extraInterfaces() does not accept null parameters."));
    }

    public static MockitoException extraInterfacesAcceptsOnlyInterfaces(Class<?> cls) {
        return new MockitoException(StringUtil.join("extraInterfaces() accepts only interfaces.", "You passed following type: " + cls.getSimpleName() + " which is not an interface."));
    }

    public static MockitoException extraInterfacesCannotContainMockedType(Class<?> cls) {
        return new MockitoException(StringUtil.join("extraInterfaces() does not accept the same type as the mocked type.", "You mocked following type: " + cls.getSimpleName(), "and you passed the same very interface to the extraInterfaces()"));
    }

    public static MockitoException extraInterfacesRequiresAtLeastOneInterface() {
        return new MockitoException(StringUtil.join("extraInterfaces() requires at least one interface."));
    }

    public static MockitoException mockedTypeIsInconsistentWithSpiedInstanceType(Class<?> cls, Object obj) {
        return new MockitoException(StringUtil.join("Mocked type must be the same as the type of your spied instance.", "Mocked type must be: " + obj.getClass().getSimpleName() + ", but is: " + cls.getSimpleName(), "  //correct spying:", "  spy = mock( ->ArrayList.class<- , withSettings().spiedInstance( ->new ArrayList()<- );", "  //incorrect - types don't match:", "  spy = mock( ->List.class<- , withSettings().spiedInstance( ->new ArrayList()<- );"));
    }

    public static MockitoException cannotCallAbstractRealMethod() {
        return new MockitoException(StringUtil.join("Cannot call abstract real method on java object!", "Calling real methods is only possible when mocking non abstract method.", "  //correct example:", "  when(mockOfConcreteClass.nonAbstractMethod()).thenCallRealMethod();"));
    }

    public static MockitoException cannotVerifyToString() {
        return new MockitoException(StringUtil.join("Mockito cannot verify toString()", "toString() is too often used behind of scenes  (i.e. during String concatenation, in IDE debugging views). Verifying it may give inconsistent or hard to understand results. Not to mention that verifying toString() most likely hints awkward design (hard to explain in a short exception message. Trust me...)", "However, it is possible to stub toString(). Stubbing toString() smells a bit funny but there are rare, legitimate use cases."));
    }

    public static MockitoException moreThanOneAnnotationNotAllowed(String str) {
        return new MockitoException("You cannot have more than one Mockito annotation on a field!\nThe field '" + str + "' has multiple Mockito annotations.\nFor info how to use annotations see examples in javadoc for MockitoAnnotations class.");
    }

    public static MockitoException unsupportedCombinationOfAnnotations(String str, String str2) {
        return new MockitoException("This combination of annotations is not permitted on a single field:\n@" + str + " and @" + str2);
    }

    public static MockitoException cannotInitializeForSpyAnnotation(String str, Exception exc) {
        return new MockitoException(StringUtil.join("Cannot instantiate a @Spy for '" + str + "' field.", "You haven't provided the instance for spying at field declaration so I tried to construct the instance.", "However, I failed because: " + exc.getMessage(), "Examples of correct usage of @Spy:", "   @Spy List mock = new LinkedList();", "   @Spy Foo foo; //only if Foo has parameterless constructor", "   //also, don't forget about MockitoAnnotations.initMocks();", ""), exc);
    }

    public static MockitoException cannotInitializeForInjectMocksAnnotation(String str, String str2) {
        return new MockitoException(StringUtil.join("Cannot instantiate @InjectMocks field named '" + str + "'! Cause: " + str2, "You haven't provided the instance at field declaration so I tried to construct the instance.", "Examples of correct usage of @InjectMocks:", "   @InjectMocks Service service = new Service();", "   @InjectMocks Service service;", "   //and... don't forget about some @Mocks for injection :)", ""));
    }

    public static MockitoException atMostAndNeverShouldNotBeUsedWithTimeout() {
        return new FriendlyReminderException(StringUtil.join("", "Don't panic! I'm just a friendly reminder!", "timeout() should not be used with atMost() or never() because...", "...it does not make much sense - the test would have passed immediately in concurrency", "We kept this method only to avoid compilation errors when upgrading Mockito.", "In future release we will remove timeout(x).atMost(y) from the API.", "If you want to find out more please refer to issue 235", ""));
    }

    public static MockitoException fieldInitialisationThrewException(Field field, Throwable th) {
        return new InjectMocksException(StringUtil.join("Cannot instantiate @InjectMocks field named '" + field.getName() + "' of type '" + field.getType() + "'.", "You haven't provided the instance at field declaration so I tried to construct the instance.", "However the constructor or the initialization block threw an exception : " + th.getMessage(), ""), th);
    }

    public static MockitoException methodDoesNotAcceptParameter(String str, String str2) {
        return new MockitoException(str + "() does not accept " + str2 + " See the Javadoc.");
    }

    public static MockitoException requiresAtLeastOneListener(String str) {
        return new MockitoException(str + "() requires at least one listener");
    }

    public static MockitoException invocationListenerThrewException(InvocationListener invocationListener, Throwable th) {
        return new MockitoException(StringUtil.join("The invocation listener with type " + invocationListener.getClass().getName(), "threw an exception : " + th.getClass().getName() + th.getMessage()), th);
    }

    public static MockitoException cannotInjectDependency(Field field, Object obj, Exception exc) {
        return new MockitoException(StringUtil.join("Mockito couldn't inject mock dependency '" + MockUtil.getMockName(obj) + "' on field ", "'" + field + "'", "whose type '" + field.getDeclaringClass().getCanonicalName() + "' was annotated by @InjectMocks in your test.", "Also I failed because: " + exceptionCauseMessageIfAvailable(exc), ""), exc);
    }

    private static String exceptionCauseMessageIfAvailable(Exception exc) {
        if (exc.getCause() == null) {
            return exc.getMessage();
        }
        return exc.getCause().getMessage();
    }

    public static MockitoException mockedTypeIsInconsistentWithDelegatedInstanceType(Class<?> cls, Object obj) {
        return new MockitoException(StringUtil.join("Mocked type must be the same as the type of your delegated instance.", "Mocked type must be: " + obj.getClass().getSimpleName() + ", but is: " + cls.getSimpleName(), "  //correct delegate:", "  spy = mock( ->List.class<- , withSettings().delegatedInstance( ->new ArrayList()<- );", "  //incorrect - types don't match:", "  spy = mock( ->List.class<- , withSettings().delegatedInstance( ->new HashSet()<- );"));
    }

    public static MockitoException spyAndDelegateAreMutuallyExclusive() {
        return new MockitoException(StringUtil.join("Settings should not define a spy instance and a delegated instance at the same time."));
    }

    public static MockitoException invalidArgumentRangeAtIdentityAnswerCreationTime() {
        return new MockitoException(StringUtil.join("Invalid argument index.", "The index need to be a positive number that indicates the position of the argument to return.", "However it is possible to use the -1 value to indicates that the last argument should be", "returned."));
    }

    public static MockitoException invalidArgumentPositionRangeAtInvocationTime(InvocationOnMock invocationOnMock, boolean z, int i) {
        String str;
        Object[] objArr = new Object[7];
        objArr[0] = "Invalid argument index for the current invocation of method : ";
        objArr[1] = " -> " + MockUtil.getMockName(invocationOnMock.getMock()) + "." + invocationOnMock.getMethod().getName() + "()";
        objArr[2] = "";
        StringBuilder sb = new StringBuilder();
        if (z) {
            str = "Last parameter wanted";
        } else {
            str = "Wanted parameter at position " + i;
        }
        sb.append(str);
        sb.append(" but ");
        sb.append(possibleArgumentTypesOf(invocationOnMock));
        objArr[3] = sb.toString();
        objArr[4] = "The index need to be a positive number that indicates a valid position of the argument in the invocation.";
        objArr[5] = "However it is possible to use the -1 value to indicates that the last argument should be returned.";
        objArr[6] = "";
        return new MockitoException(StringUtil.join(objArr));
    }

    private static StringBuilder possibleArgumentTypesOf(InvocationOnMock invocationOnMock) {
        Class[] parameterTypes = invocationOnMock.getMethod().getParameterTypes();
        if (parameterTypes.length == 0) {
            return new StringBuilder("the method has no arguments.\n");
        }
        StringBuilder sb = new StringBuilder("the possible argument indexes for this method are :\n");
        int length = parameterTypes.length;
        for (int i = 0; i < length; i++) {
            sb.append("    [");
            sb.append(i);
            if (!invocationOnMock.getMethod().isVarArgs() || i != length - 1) {
                sb.append("] ");
                sb.append(parameterTypes[i].getSimpleName());
                sb.append(IOUtils.LINE_SEPARATOR_UNIX);
            } else {
                sb.append("+] ");
                sb.append(parameterTypes[i].getComponentType().getSimpleName());
                sb.append("  <- Vararg");
                sb.append(IOUtils.LINE_SEPARATOR_UNIX);
            }
        }
        return sb;
    }

    public static MockitoException wrongTypeOfArgumentToReturn(InvocationOnMock invocationOnMock, String str, Class<?> cls, int i) {
        return new WrongTypeOfReturnValue(StringUtil.join("The argument of type '" + cls.getSimpleName() + "' cannot be returned because the following ", "method should return the type '" + str + "'", " -> " + MockUtil.getMockName(invocationOnMock.getMock()) + "." + invocationOnMock.getMethod().getName() + "()", "", "The reason for this error can be :", "1. The wanted argument position is incorrect.", "2. The answer is used on the wrong interaction.", "", "Position of the wanted argument is " + i + " and " + possibleArgumentTypesOf(invocationOnMock), "***", "However if you're still unsure why you're getting above error read on.", "Due to the nature of the syntax above problem might occur because:", "1. This exception *might* occur in wrongly written multi-threaded tests.", "   Please refer to Mockito FAQ on limitations of concurrency testing.", "2. A spy is stubbed using when(spy.foo()).then() syntax. It is safer to stub spies - ", "   - with doReturn|Throw() family of methods. More in javadocs for Mockito.spy() method.", ""));
    }

    public static MockitoException defaultAnswerDoesNotAcceptNullParameter() {
        return new MockitoException("defaultAnswer() does not accept null parameter");
    }

    public static MockitoException serializableWontWorkForObjectsThatDontImplementSerializable(Class<?> cls) {
        return new MockitoException(StringUtil.join("You are using the setting 'withSettings().serializable()' however the type you are trying to mock '" + cls.getSimpleName() + "'", "do not implement Serializable AND do not have a no-arg constructor.", "This combination is requested, otherwise you will get an 'java.io.InvalidClassException' when the mock will be serialized", "", "Also note that as requested by the Java serialization specification, the whole hierarchy need to implements Serializable,", "i.e. the top-most superclass has to implements Serializable.", ""));
    }

    public static MockitoException delegatedMethodHasWrongReturnType(Method method, Method method2, Object obj, Object obj2) {
        return new MockitoException(StringUtil.join("Methods called on delegated instance must have compatible return types with the mock.", "When calling: " + method + " on mock: " + MockUtil.getMockName(obj), "return type should be: " + method.getReturnType().getSimpleName() + ", but was: " + method2.getReturnType().getSimpleName(), "Check that the instance passed to delegatesTo() is of the correct type or contains compatible methods", "(delegate instance had type: " + obj2.getClass().getSimpleName() + ")"));
    }

    public static MockitoException delegatedMethodDoesNotExistOnDelegate(Method method, Object obj, Object obj2) {
        return new MockitoException(StringUtil.join("Methods called on mock must exist in delegated instance.", "When calling: " + method + " on mock: " + MockUtil.getMockName(obj), "no such method was found.", "Check that the instance passed to delegatesTo() is of the correct type or contains compatible methods", "(delegate instance had type: " + obj2.getClass().getSimpleName() + ")"));
    }

    public static MockitoException usingConstructorWithFancySerializable(SerializableMode serializableMode) {
        return new MockitoException("Mocks instantiated with constructor cannot be combined with " + serializableMode + " serialization mode.");
    }

    public static MockitoException cannotCreateTimerWithNegativeDurationTime(long j) {
        return new FriendlyReminderException(StringUtil.join("", "Don't panic! I'm just a friendly reminder!", "It is impossible for time to go backward, therefore...", "You cannot put negative value of duration: (" + j + ")", "as argument of timer methods (after(), timeout())", ""));
    }

    public static MockitoException notAnException() {
        return new MockitoException(StringUtil.join("Exception type cannot be null.", "This may happen with doThrow(Class)|thenThrow(Class) family of methods if passing null parameter."));
    }

    public static UnnecessaryStubbingException formatUnncessaryStubbingException(Class<?> cls, Collection<Invocation> collection) {
        String str;
        StringBuilder sb = new StringBuilder();
        int i = 1;
        for (Invocation location : collection) {
            sb.append("\n  ");
            sb.append(i);
            sb.append(". ");
            sb.append(location.getLocation());
            i++;
        }
        if (cls != null) {
            str = "Unnecessary stubbings detected in test class: " + cls.getSimpleName();
        } else {
            str = "Unnecessary stubbings detected.";
        }
        return new UnnecessaryStubbingException(StringUtil.join(str, "Clean & maintainable test code requires zero unnecessary code.", "Following stubbings are unnecessary (click to navigate to relevant line of code):" + sb, "Please remove unnecessary stubbings or use 'lenient' strictness. More info: javadoc for UnnecessaryStubbingException class."));
    }

    public static void unncessaryStubbingException(List<Invocation> list) {
        throw formatUnncessaryStubbingException((Class<?>) null, list);
    }

    public static void potentialStubbingProblem(Invocation invocation, Collection<Invocation> collection) {
        StringBuilder sb = new StringBuilder();
        int i = 1;
        for (Invocation next : collection) {
            sb.append("    ");
            sb.append(i);
            sb.append(". ");
            sb.append(next);
            sb.append("\n      ");
            sb.append(next.getLocation());
            sb.append(IOUtils.LINE_SEPARATOR_UNIX);
            i++;
        }
        sb.deleteCharAt(sb.length() - 1);
        throw new PotentialStubbingProblem(StringUtil.join("Strict stubbing argument mismatch. Please check:", " - this invocation of '" + invocation.getMethod().getName() + "' method:", "    " + invocation, "    " + invocation.getLocation(), " - has following stubbing(s) with different arguments:", sb, "Typically, stubbing argument mismatch indicates user mistake when writing tests.", "Mockito fails early so that you can debug potential problem easily.", "However, there are legit scenarios when this exception generates false negative signal:", "  - stubbing the same method multiple times using 'given().will()' or 'when().then()' API", "    Please use 'will().given()' or 'doReturn().when()' API for stubbing.", "  - stubbed method is intentionally invoked with different arguments by code under test", "    Please use default or 'silent' JUnit Rule (equivalent of Strictness.LENIENT).", "For more information see javadoc for PotentialStubbingProblem class."));
    }

    public static void redundantMockitoListener(String str) {
        throw new RedundantListenerException(StringUtil.join("Problems adding Mockito listener.", "Listener of type '" + str + "' has already been added and not removed.", "It indicates that previous listener was not removed according to the API.", "When you add a listener, don't forget to remove the listener afterwards:", "  Mockito.framework().removeListener(myListener);", "For more information, see the javadoc for RedundantListenerException class."));
    }

    public static void unfinishedMockingSession() {
        throw new UnfinishedMockingSessionException(StringUtil.join("Unfinished mocking session detected.", "Previous MockitoSession was not concluded with 'finishMocking()'.", "For examples of correct usage see javadoc for MockitoSession class."));
    }
}
