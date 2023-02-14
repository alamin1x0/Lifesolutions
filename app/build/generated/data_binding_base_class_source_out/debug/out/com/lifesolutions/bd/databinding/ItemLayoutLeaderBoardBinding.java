// Generated by view binder compiler. Do not edit!
package com.lifesolutions.bd.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.lifesolutions.bd.R;
import de.hdodenhof.circleimageview.CircleImageView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ItemLayoutLeaderBoardBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final TextView coinLeaderBoardItem;

  @NonNull
  public final TextView nameLeaderBoardItem;

  @NonNull
  public final CircleImageView profileImageLeaderBoardItem;

  @NonNull
  public final TextView rankLeaderBoardItem;

  @NonNull
  public final RelativeLayout userItemLeaderBoard;

  private ItemLayoutLeaderBoardBinding(@NonNull RelativeLayout rootView,
      @NonNull TextView coinLeaderBoardItem, @NonNull TextView nameLeaderBoardItem,
      @NonNull CircleImageView profileImageLeaderBoardItem, @NonNull TextView rankLeaderBoardItem,
      @NonNull RelativeLayout userItemLeaderBoard) {
    this.rootView = rootView;
    this.coinLeaderBoardItem = coinLeaderBoardItem;
    this.nameLeaderBoardItem = nameLeaderBoardItem;
    this.profileImageLeaderBoardItem = profileImageLeaderBoardItem;
    this.rankLeaderBoardItem = rankLeaderBoardItem;
    this.userItemLeaderBoard = userItemLeaderBoard;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ItemLayoutLeaderBoardBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ItemLayoutLeaderBoardBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.item_layout_leader_board, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ItemLayoutLeaderBoardBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.coin_leader_board_item;
      TextView coinLeaderBoardItem = ViewBindings.findChildViewById(rootView, id);
      if (coinLeaderBoardItem == null) {
        break missingId;
      }

      id = R.id.name_leader_board_item;
      TextView nameLeaderBoardItem = ViewBindings.findChildViewById(rootView, id);
      if (nameLeaderBoardItem == null) {
        break missingId;
      }

      id = R.id.profile_image_leader_board_item;
      CircleImageView profileImageLeaderBoardItem = ViewBindings.findChildViewById(rootView, id);
      if (profileImageLeaderBoardItem == null) {
        break missingId;
      }

      id = R.id.rank_leader_board_item;
      TextView rankLeaderBoardItem = ViewBindings.findChildViewById(rootView, id);
      if (rankLeaderBoardItem == null) {
        break missingId;
      }

      RelativeLayout userItemLeaderBoard = (RelativeLayout) rootView;

      return new ItemLayoutLeaderBoardBinding((RelativeLayout) rootView, coinLeaderBoardItem,
          nameLeaderBoardItem, profileImageLeaderBoardItem, rankLeaderBoardItem,
          userItemLeaderBoard);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}