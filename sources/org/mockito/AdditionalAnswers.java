package org.mockito;

import java.util.Collection;
import org.mockito.internal.stubbing.answers.AnswerFunctionalInterfaces;
import org.mockito.internal.stubbing.answers.AnswersWithDelay;
import org.mockito.internal.stubbing.answers.ReturnsArgumentAt;
import org.mockito.internal.stubbing.answers.ReturnsElementsOf;
import org.mockito.internal.stubbing.defaultanswers.ForwardsInvocations;
import org.mockito.stubbing.Answer;
import org.mockito.stubbing.Answer1;
import org.mockito.stubbing.Answer2;
import org.mockito.stubbing.Answer3;
import org.mockito.stubbing.Answer4;
import org.mockito.stubbing.Answer5;
import org.mockito.stubbing.VoidAnswer1;
import org.mockito.stubbing.VoidAnswer2;
import org.mockito.stubbing.VoidAnswer3;
import org.mockito.stubbing.VoidAnswer4;
import org.mockito.stubbing.VoidAnswer5;

public class AdditionalAnswers {
    public static <T> Answer<T> returnsFirstArg() {
        return new ReturnsArgumentAt(0);
    }

    public static <T> Answer<T> returnsSecondArg() {
        return new ReturnsArgumentAt(1);
    }

    public static <T> Answer<T> returnsLastArg() {
        return new ReturnsArgumentAt(-1);
    }

    public static <T> Answer<T> returnsArgAt(int i) {
        return new ReturnsArgumentAt(i);
    }

    public static <T> Answer<T> delegatesTo(Object obj) {
        return new ForwardsInvocations(obj);
    }

    public static <T> Answer<T> returnsElementsOf(Collection<?> collection) {
        return new ReturnsElementsOf(collection);
    }

    @Incubating
    public static <T> Answer<T> answersWithDelay(long j, Answer<T> answer) {
        return new AnswersWithDelay(j, answer);
    }

    @Incubating
    public static <T, A> Answer<T> answer(Answer1<T, A> answer1) {
        return AnswerFunctionalInterfaces.toAnswer(answer1);
    }

    @Incubating
    public static <A> Answer<Void> answerVoid(VoidAnswer1<A> voidAnswer1) {
        return AnswerFunctionalInterfaces.toAnswer(voidAnswer1);
    }

    @Incubating
    public static <T, A, B> Answer<T> answer(Answer2<T, A, B> answer2) {
        return AnswerFunctionalInterfaces.toAnswer(answer2);
    }

    @Incubating
    public static <A, B> Answer<Void> answerVoid(VoidAnswer2<A, B> voidAnswer2) {
        return AnswerFunctionalInterfaces.toAnswer(voidAnswer2);
    }

    @Incubating
    public static <T, A, B, C> Answer<T> answer(Answer3<T, A, B, C> answer3) {
        return AnswerFunctionalInterfaces.toAnswer(answer3);
    }

    @Incubating
    public static <A, B, C> Answer<Void> answerVoid(VoidAnswer3<A, B, C> voidAnswer3) {
        return AnswerFunctionalInterfaces.toAnswer(voidAnswer3);
    }

    @Incubating
    public static <T, A, B, C, D> Answer<T> answer(Answer4<T, A, B, C, D> answer4) {
        return AnswerFunctionalInterfaces.toAnswer(answer4);
    }

    @Incubating
    public static <A, B, C, D> Answer<Void> answerVoid(VoidAnswer4<A, B, C, D> voidAnswer4) {
        return AnswerFunctionalInterfaces.toAnswer(voidAnswer4);
    }

    @Incubating
    public static <T, A, B, C, D, E> Answer<T> answer(Answer5<T, A, B, C, D, E> answer5) {
        return AnswerFunctionalInterfaces.toAnswer(answer5);
    }

    @Incubating
    public static <A, B, C, D, E> Answer<Void> answerVoid(VoidAnswer5<A, B, C, D, E> voidAnswer5) {
        return AnswerFunctionalInterfaces.toAnswer(voidAnswer5);
    }
}
