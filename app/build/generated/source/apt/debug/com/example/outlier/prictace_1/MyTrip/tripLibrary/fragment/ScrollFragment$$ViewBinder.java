// Generated code from Butter Knife. Do not modify!
package com.example.outlier.prictace_1.MyTrip.tripLibrary.fragment;

import android.support.v4.widget.NestedScrollView;
import butterknife.Unbinder;
import butterknife.internal.Finder;
import butterknife.internal.ViewBinder;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class ScrollFragment$$ViewBinder<T extends ScrollFragment> implements ViewBinder<T> {
  @Override
  public Unbinder bind(Finder finder, T target, Object source) {
    return new InnerUnbinder<>(target, finder, source);
  }

  protected static class InnerUnbinder<T extends ScrollFragment> implements Unbinder {
    protected T target;

    protected InnerUnbinder(T target, Finder finder, Object source) {
      this.target = target;

      target.mScrollView = finder.findRequiredViewAsType(source, 2131689654, "field 'mScrollView'", NestedScrollView.class);
    }

    @Override
    public void unbind() {
      T target = this.target;
      if (target == null) throw new IllegalStateException("Bindings already cleared.");

      target.mScrollView = null;

      this.target = null;
    }
  }
}
