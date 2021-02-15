package org.simpleframework.xml.core;

interface Creator {
    Object getInstance() throws Exception;

    Object getInstance(Criteria criteria) throws Exception;

    double getScore(Criteria criteria) throws Exception;

    Signature getSignature() throws Exception;

    Class getType() throws Exception;
}
