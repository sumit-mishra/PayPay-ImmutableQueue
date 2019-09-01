package com.paypay.immutableq.intrface;

public interface CloneableQ<T> extends Cloneable {
  T clone();
}
