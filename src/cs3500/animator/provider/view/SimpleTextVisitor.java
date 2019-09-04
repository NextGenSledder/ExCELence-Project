package cs3500.animator.provider.view;

import cs3500.animator.provider.adapter.ImmutableShape;

public class SimpleTextVisitor implements ViewVisitor {

  @Override
  public String visitShape(ImmutableShape s) throws IllegalArgumentException {
    if (s == null) {
      throw new IllegalArgumentException("arguments cannot be null");
    }

    return s.accept(this);
  }

  @Override
  public String visitRect(ImmutableShape r) throws IllegalArgumentException {
    if (r == null) {
      throw new IllegalArgumentException("arguments cannot be null");
    }

    return r.getName() + " rectangle";
  }

  @Override
  public String visitEllipse(ImmutableShape e) throws IllegalArgumentException {
    if (e == null) {
      throw new IllegalArgumentException("arguments cannot be null");
    }

    return e.getName() + " ellipse";
  }
}
