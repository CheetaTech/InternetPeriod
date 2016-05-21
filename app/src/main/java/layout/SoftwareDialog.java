package layout;

import android.app.Dialog;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.mobiledatatimerwidget.R;


public class SoftwareDialog extends DialogFragment implements View.OnClickListener,NumberPicker.OnValueChangeListener {

    public static final String EXTRA_DEFAULT_FRAGMENT = "com.mobiledatatimerwidget.EXTRA_DEFAULT_FRAGMENT";

    public static final int FRAGMENT_STATISTICS = 0;
    public static final int FRAGMENT_DISCOVER = 1;
    private OnFragmentInteractionListener mListener;

    public SoftwareDialog() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    public static SoftwareDialog newInstance() {
        SoftwareDialog fragment = new SoftwareDialog();
        return fragment;
    }
    @Override
    public void onResume()
    {
        super.onResume();
        Log.e("onResume", "onResume");
        this.getDialog().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Log.e("OnCreateDialog", "OnCreateDialog");
        Dialog dialog = new Dialog(getActivity(), android.R.style.Theme_DeviceDefault_Light_Dialog_NoActionBar);
        View view = getActivity().getLayoutInflater().inflate(R.layout.fragment_software_dialog, null);
        dialog.getWindow().setContentView(view);

        dialog.setCanceledOnTouchOutside(false);

        ((Button)view.findViewById(R.id.setTimeButton)).setOnClickListener(this);

        NumberPicker np  = (NumberPicker) view.findViewById(R.id.numberPickerTime);
        np.setMinValue(0);
        np.setMaxValue(60);
        np.setWrapSelectorWheel(true);
        np.setOnValueChangedListener(this);
        np.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);



        return dialog;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.e("onViewCreated","onViewCreated");
    }
        @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.setTimeButton:
                    getDialog().dismiss();
                break;
        }
    }

    @Override
    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
        switch (picker.getId())
        {
            case R.id.numberPickerTime:
                    Log.e("TAG","Picker Old value: "+oldVal + " newVal "+ newVal);
                break;
            default:
                break;
        }
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}

