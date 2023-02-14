package com.lifesolutions.bd.kotlinCode.ui.home.menu;

import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.lifesolutions.bd.R;

public class MenuFragmentDirections {
  private MenuFragmentDirections() {
  }

  @NonNull
  public static NavDirections actionMenuFragmentToConversationsFragment() {
    return new ActionOnlyNavDirections(R.id.action_menuFragment_to_conversationsFragment);
  }
}
