package sa48.team11.adproject.utils;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;

import sa48.team11.adproject.listeners.IMyAnimFinishListener;

public class TypeWriter extends android.support.v7.widget.AppCompatTextView {
    private CharSequence text;
    private int index;
    private long delay = 150; // in ms
    private IMyAnimFinishListener listener;
    public TypeWriter(Context context) {
        super(context);
    }
    public TypeWriter(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    private Handler mHandler = new Handler();
    private Runnable characterAdder = new Runnable() {
        @Override
        public void run() {
            setText(text.subSequence(0, index++));
            if (index <= text.length()) {
                mHandler.postDelayed(characterAdder, delay);
            }
            if(index == text.length()) {
                Log.d("Welcome","TypeWriter "+index);
                listener.animFinish();
                mHandler.removeCallbacks(characterAdder);
                return;
            }
        }
    };
    public void animateText(CharSequence txt, IMyAnimFinishListener listener) {
        this.listener = listener;
        text = txt;
        index = 0;
        setText("");
        mHandler.removeCallbacks(characterAdder);
        mHandler.postDelayed(characterAdder, delay);
    }
    public void setCharacterDelay(long m) {
        delay = m;
    }
}