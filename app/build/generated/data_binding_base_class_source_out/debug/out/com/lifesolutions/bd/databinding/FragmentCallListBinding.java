// Generated by view binder compiler. Do not edit!
package com.lifesolutions.bd.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.lifesolutions.bd.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentCallListBinding implements ViewBinding {
  @NonNull
  private final FrameLayout rootView;

  @NonNull
  public final FloatingActionButton btnDeleteCallLog;

  @NonNull
  public final LottieAnimationView lottieAnimationCallList;

  @NonNull
  public final RecyclerView recyclerViewCallList;

  @NonNull
  public final TextView tvNoCallLogs;

  private FragmentCallListBinding(@NonNull FrameLayout rootView,
      @NonNull FloatingActionButton btnDeleteCallLog,
      @NonNull LottieAnimationView lottieAnimationCallList,
      @NonNull RecyclerView recyclerViewCallList, @NonNull TextView tvNoCallLogs) {
    this.rootView = rootView;
    this.btnDeleteCallLog = btnDeleteCallLog;
    this.lottieAnimationCallList = lottieAnimationCallList;
    this.recyclerViewCallList = recyclerViewCallList;
    this.tvNoCallLogs = tvNoCallLogs;
  }

  @Override
  @NonNull
  public FrameLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentCallListBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentCallListBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_call_list, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentCallListBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btn_delete_call_log;
      FloatingActionButton btnDeleteCallLog = ViewBindings.findChildViewById(rootView, id);
      if (btnDeleteCallLog == null) {
        break missingId;
      }

      id = R.id.lottie_animation_call_list;
      LottieAnimationView lottieAnimationCallList = ViewBindings.findChildViewById(rootView, id);
      if (lottieAnimationCallList == null) {
        break missingId;
      }

      id = R.id.recycler_view_call_list;
      RecyclerView recyclerViewCallList = ViewBindings.findChildViewById(rootView, id);
      if (recyclerViewCallList == null) {
        break missingId;
      }

      id = R.id.tv_no_call_logs;
      TextView tvNoCallLogs = ViewBindings.findChildViewById(rootView, id);
      if (tvNoCallLogs == null) {
        break missingId;
      }

      return new FragmentCallListBinding((FrameLayout) rootView, btnDeleteCallLog,
          lottieAnimationCallList, recyclerViewCallList, tvNoCallLogs);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
