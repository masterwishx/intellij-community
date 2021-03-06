// Copyright 2000-2020 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
package com.intellij.util;

import com.intellij.util.containers.ContainerUtil;
import com.intellij.util.containers.SingletonIterator;
import gnu.trove.TObjectHashingStrategy;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

/**
 * Read-only set consisting of the only element
 * @deprecated Use {@link java.util.Collections#singleton(Object)}
 */
@Deprecated
@ApiStatus.ScheduledForRemoval(inVersion = "2021.2")
public class SingletonSet<E> implements Set<E> {
  private final E theElement;

  public SingletonSet(E e) {
    theElement = e;
  }

  @Override
  public int size() {
    return 1;
  }

  @Override
  public boolean contains(Object elem) {
    //noinspection unchecked
    return getStrategy().equals(theElement, (E)elem);
  }

  @NotNull
  @Override
  public Iterator<E> iterator() {
    return new SingletonIterator<>(theElement);
  }

  @Override
  public Object @NotNull [] toArray() {
    return new Object[]{theElement};
  }

  @Override
  public <T> T @NotNull [] toArray(T @NotNull [] a) {
    if (a.length == 0) {
      a = ArrayUtil.newArray(ArrayUtil.getComponentType(a), 1);
    }
    //noinspection unchecked
    a[0] = (T)theElement;
    if (a.length > 1) {
        a[1] = null;
    }
    return a;
  }

  @Override
  public boolean add(E t) {
    throw new IncorrectOperationException();
  }

  @Override
  public boolean remove(Object o) {
    throw new IncorrectOperationException();
  }

  @Override
  public boolean containsAll(@NotNull Collection<?> c) {
    for (Object e : c) {
      if (!contains(e)) {
        return false;
      }
    }
    return true;
  }

  @Override
  public boolean addAll(@NotNull Collection<? extends E> c) {
    throw new IncorrectOperationException();
  }

  @Override
  public boolean retainAll(@NotNull Collection<?> c) {
    throw new IncorrectOperationException();
  }

  @Override
  public boolean removeAll(@NotNull Collection<?> c) {
    throw new IncorrectOperationException();
  }

  @Override
  public void clear() {
    throw new IncorrectOperationException();
  }

  @Override
  public boolean isEmpty() {
    return false;
  }

  @NotNull
  protected TObjectHashingStrategy<E> getStrategy() {
    return ContainerUtil.canonicalStrategy();
  }
}
