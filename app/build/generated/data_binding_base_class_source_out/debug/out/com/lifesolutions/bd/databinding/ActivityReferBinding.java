// Generated by view binder compiler. Do not edit!
package com.lifesolutions.bd.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.appbar.AppBarLayout;
import com.lifesolutions.bd.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityReferBinding implements ViewBinding {
  @NonNull
  private final NestedScrollView rootView;

  @NonNull
  public final AppBarLayout appBarRefer;

  @NonNull
  public final TextView codeReferCode;

  @NonNull
  public final ImageView coinImgView;

  @NonNull
  public final TextView coinReferCode;

  @NonNull
  public final TextView copyReferCode;

  @NonNull
  public final TextView copyReferTextOne;

  @NonNull
  public final TextView itemDescription;

  @NonNull
  public final TextView itemDescription2;

  @NonNull
  public final TextView itemDescription3;

  @NonNull
  public final ImageView itemDescriptionImg;

  @NonNull
  public final ImageView itemDescriptionImg2;

  @NonNull
  public final ImageView itemDescriptionImg3;

  @NonNull
  public final LinearLayout itemDescriptionLayout;

  @NonNull
  public final LinearLayout itemDescriptionLayout2;

  @NonNull
  public final LinearLayout itemDescriptionLayout3;

  @NonNull
  public final TextView itemDescriptionTitle;

  @NonNull
  public final TextView itemDescriptionTitle2;

  @NonNull
  public final TextView itemDescriptionTitle3;

  @NonNull
  public final LinearLayout referCodeViewArea;

  @NonNull
  public final Button shareBtnReferCode;

  @NonNull
  public final Toolbar toolbarRefer;

  @NonNull
  public final RelativeLayout topView;

  @NonNull
  public final TextView totalReferredReferCode;

  private ActivityReferBinding(@NonNull NestedScrollView rootView,
      @NonNull AppBarLayout appBarRefer, @NonNull TextView codeReferCode,
      @NonNull ImageView coinImgView, @NonNull TextView coinReferCode,
      @NonNull TextView copyReferCode, @NonNull TextView copyReferTextOne,
      @NonNull TextView itemDescription, @NonNull TextView itemDescription2,
      @NonNull TextView itemDescription3, @NonNull ImageView itemDescriptionImg,
      @NonNull ImageView itemDescriptionImg2, @NonNull ImageView itemDescriptionImg3,
      @NonNull LinearLayout itemDescriptionLayout, @NonNull LinearLayout itemDescriptionLayout2,
      @NonNull LinearLayout itemDescriptionLayout3, @NonNull TextView itemDescriptionTitle,
      @NonNull TextView itemDescriptionTitle2, @NonNull TextView itemDescriptionTitle3,
      @NonNull LinearLayout referCodeViewArea, @NonNull Button shareBtnReferCode,
      @NonNull Toolbar toolbarRefer, @NonNull RelativeLayout topView,
      @NonNull TextView totalReferredReferCode) {
    this.rootView = rootView;
    this.appBarRefer = appBarRefer;
    this.codeReferCode = codeReferCode;
    this.coinImgView = coinImgView;
    this.coinReferCode = coinReferCode;
    this.copyReferCode = copyReferCode;
    this.copyReferTextOne = copyReferTextOne;
    this.itemDescription = itemDescription;
    this.itemDescription2 = itemDescription2;
    this.itemDescription3 = itemDescription3;
    this.itemDescriptionImg = itemDescriptionImg;
    this.itemDescriptionImg2 = itemDescriptionImg2;
    this.itemDescriptionImg3 = itemDescriptionImg3;
    this.itemDescriptionLayout = itemDescriptionLayout;
    this.itemDescriptionLayout2 = itemDescriptionLayout2;
    this.itemDescriptionLayout3 = itemDescriptionLayout3;
    this.itemDescriptionTitle = itemDescriptionTitle;
    this.itemDescriptionTitle2 = itemDescriptionTitle2;
    this.itemDescriptionTitle3 = itemDescriptionTitle3;
    this.referCodeViewArea = referCodeViewArea;
    this.shareBtnReferCode = shareBtnReferCode;
    this.toolbarRefer = toolbarRefer;
    this.topView = topView;
    this.totalReferredReferCode = totalReferredReferCode;
  }

  @Override
  @NonNull
  public NestedScrollView getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityReferBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityReferBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_refer, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityReferBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.appBar_refer;
      AppBarLayout appBarRefer = ViewBindings.findChildViewById(rootView, id);
      if (appBarRefer == null) {
        break missingId;
      }

      id = R.id.code_refer_code;
      TextView codeReferCode = ViewBindings.findChildViewById(rootView, id);
      if (codeReferCode == null) {
        break missingId;
      }

      id = R.id.coin_img_view;
      ImageView coinImgView = ViewBindings.findChildViewById(rootView, id);
      if (coinImgView == null) {
        break missingId;
      }

      id = R.id.coin_refer_code;
      TextView coinReferCode = ViewBindings.findChildViewById(rootView, id);
      if (coinReferCode == null) {
        break missingId;
      }

      id = R.id.copy_refer_code;
      TextView copyReferCode = ViewBindings.findChildViewById(rootView, id);
      if (copyReferCode == null) {
        break missingId;
      }

      id = R.id.copy_refer_text_one;
      TextView copyReferTextOne = ViewBindings.findChildViewById(rootView, id);
      if (copyReferTextOne == null) {
        break missingId;
      }

      id = R.id.item_description;
      TextView itemDescription = ViewBindings.findChildViewById(rootView, id);
      if (itemDescription == null) {
        break missingId;
      }

      id = R.id.item_description2;
      TextView itemDescription2 = ViewBindings.findChildViewById(rootView, id);
      if (itemDescription2 == null) {
        break missingId;
      }

      id = R.id.item_description3;
      TextView itemDescription3 = ViewBindings.findChildViewById(rootView, id);
      if (itemDescription3 == null) {
        break missingId;
      }

      id = R.id.item_description_img;
      ImageView itemDescriptionImg = ViewBindings.findChildViewById(rootView, id);
      if (itemDescriptionImg == null) {
        break missingId;
      }

      id = R.id.item_description_img2;
      ImageView itemDescriptionImg2 = ViewBindings.findChildViewById(rootView, id);
      if (itemDescriptionImg2 == null) {
        break missingId;
      }

      id = R.id.item_description_img3;
      ImageView itemDescriptionImg3 = ViewBindings.findChildViewById(rootView, id);
      if (itemDescriptionImg3 == null) {
        break missingId;
      }

      id = R.id.item_description_layout;
      LinearLayout itemDescriptionLayout = ViewBindings.findChildViewById(rootView, id);
      if (itemDescriptionLayout == null) {
        break missingId;
      }

      id = R.id.item_description_layout2;
      LinearLayout itemDescriptionLayout2 = ViewBindings.findChildViewById(rootView, id);
      if (itemDescriptionLayout2 == null) {
        break missingId;
      }

      id = R.id.item_description_layout3;
      LinearLayout itemDescriptionLayout3 = ViewBindings.findChildViewById(rootView, id);
      if (itemDescriptionLayout3 == null) {
        break missingId;
      }

      id = R.id.item_description_title;
      TextView itemDescriptionTitle = ViewBindings.findChildViewById(rootView, id);
      if (itemDescriptionTitle == null) {
        break missingId;
      }

      id = R.id.item_description_title2;
      TextView itemDescriptionTitle2 = ViewBindings.findChildViewById(rootView, id);
      if (itemDescriptionTitle2 == null) {
        break missingId;
      }

      id = R.id.item_description_title3;
      TextView itemDescriptionTitle3 = ViewBindings.findChildViewById(rootView, id);
      if (itemDescriptionTitle3 == null) {
        break missingId;
      }

      id = R.id.refer_code_view_area;
      LinearLayout referCodeViewArea = ViewBindings.findChildViewById(rootView, id);
      if (referCodeViewArea == null) {
        break missingId;
      }

      id = R.id.share_btn_refer_code;
      Button shareBtnReferCode = ViewBindings.findChildViewById(rootView, id);
      if (shareBtnReferCode == null) {
        break missingId;
      }

      id = R.id.toolbar_refer;
      Toolbar toolbarRefer = ViewBindings.findChildViewById(rootView, id);
      if (toolbarRefer == null) {
        break missingId;
      }

      id = R.id.top_view;
      RelativeLayout topView = ViewBindings.findChildViewById(rootView, id);
      if (topView == null) {
        break missingId;
      }

      id = R.id.total_referred_refer_code;
      TextView totalReferredReferCode = ViewBindings.findChildViewById(rootView, id);
      if (totalReferredReferCode == null) {
        break missingId;
      }

      return new ActivityReferBinding((NestedScrollView) rootView, appBarRefer, codeReferCode,
          coinImgView, coinReferCode, copyReferCode, copyReferTextOne, itemDescription,
          itemDescription2, itemDescription3, itemDescriptionImg, itemDescriptionImg2,
          itemDescriptionImg3, itemDescriptionLayout, itemDescriptionLayout2,
          itemDescriptionLayout3, itemDescriptionTitle, itemDescriptionTitle2,
          itemDescriptionTitle3, referCodeViewArea, shareBtnReferCode, toolbarRefer, topView,
          totalReferredReferCode);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
