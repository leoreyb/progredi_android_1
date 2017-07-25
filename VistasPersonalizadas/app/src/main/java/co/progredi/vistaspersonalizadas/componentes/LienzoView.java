package co.progredi.vistaspersonalizadas.componentes;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lrey on 7/24/17.
 */

public class LienzoView extends View {

    private Path linea;
    private Paint pincel;
    private List<LineaDTO> listaLineas = new ArrayList<>();
    private int color;

    public LienzoView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        pincel = new Paint();
        pincel.setColor(Color.RED);
        pincel.setStyle(Paint.Style.STROKE);
        pincel.setStrokeWidth(20);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        for (LineaDTO lineaGuardada : listaLineas) {
            pincel.setColor(lineaGuardada.color);
            canvas.drawPath(lineaGuardada.linea, pincel);
        }
        if (linea != null) {
            pincel.setColor(color);
            canvas.drawPath(linea, pincel);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                linea = new Path();
                linea.moveTo(event.getX(), event.getY());
                break;
            case MotionEvent.ACTION_UP:
                LineaDTO lineaDTO = new LineaDTO();
                lineaDTO.linea = linea;
                lineaDTO.color = color;
                listaLineas.add(lineaDTO);
                linea = null;
                break;
            case MotionEvent.ACTION_MOVE:
                linea.lineTo(event.getX(), event.getY());
                break;
        }
        invalidate();
        return true;

    }

    public void setColor(int color) {
        this.color = color;
        pincel.setColor(color);
    }


    private final class LineaDTO {

        private Path linea;
        private int color;
    }

}
