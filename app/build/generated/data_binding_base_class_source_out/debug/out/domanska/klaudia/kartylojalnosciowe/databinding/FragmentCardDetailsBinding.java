// Generated by view binder compiler. Do not edit!
package domanska.klaudia.kartylojalnosciowe.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import domanska.klaudia.kartylojalnosciowe.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentCardDetailsBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final ImageView cardImageView;

  @NonNull
  public final EditText imageInputName;

  @NonNull
  public final Button saveButton;

  private FragmentCardDetailsBinding(@NonNull ConstraintLayout rootView,
      @NonNull ImageView cardImageView, @NonNull EditText imageInputName,
      @NonNull Button saveButton) {
    this.rootView = rootView;
    this.cardImageView = cardImageView;
    this.imageInputName = imageInputName;
    this.saveButton = saveButton;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentCardDetailsBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentCardDetailsBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_card_details, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentCardDetailsBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.card_image_view;
      ImageView cardImageView = ViewBindings.findChildViewById(rootView, id);
      if (cardImageView == null) {
        break missingId;
      }

      id = R.id.image_input_name;
      EditText imageInputName = ViewBindings.findChildViewById(rootView, id);
      if (imageInputName == null) {
        break missingId;
      }

      id = R.id.save_button;
      Button saveButton = ViewBindings.findChildViewById(rootView, id);
      if (saveButton == null) {
        break missingId;
      }

      return new FragmentCardDetailsBinding((ConstraintLayout) rootView, cardImageView,
          imageInputName, saveButton);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
