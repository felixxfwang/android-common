package androidx.navigation.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;

import org.tiramisu.navigation.OnDestinationChangedListener;

@SuppressLint("RestrictedApi")
public class TiramisuOnDestinationChangedListener extends ActionBarOnDestinationChangedListener {

    private OnDestinationChangedListener listener;

    public TiramisuOnDestinationChangedListener(
            @NonNull AppCompatActivity activity,
            @NonNull AppBarConfiguration configuration,
            @Nullable OnDestinationChangedListener listener
    ) {
        super(activity, configuration);
        this.listener = listener;
    }

    @Override
    protected void setTitle(CharSequence title) {
        if (listener == null) {
            super.setTitle(title);
        } else {
            listener.setActionBarTitle(title);
        }
    }

    @Override
    public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
        super.onDestinationChanged(controller, destination, arguments);
        if (listener != null) {
            listener.onDestinationChanged(controller, destination, arguments);
        }
    }
}
