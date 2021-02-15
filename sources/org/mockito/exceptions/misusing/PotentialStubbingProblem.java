package org.mockito.exceptions.misusing;

import org.mockito.exceptions.base.MockitoException;

public class PotentialStubbingProblem extends MockitoException {
    public PotentialStubbingProblem(String str) {
        super(str);
    }
}
