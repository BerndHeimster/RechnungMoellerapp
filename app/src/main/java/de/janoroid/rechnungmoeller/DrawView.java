package de.janoroid.rechnungmoeller;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Environment;
import android.view.MotionEvent;
import android.view.View;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

class DrawView extends View {

    private Paint paint = new Paint();
    private float lastX, lastY;
    private boolean drawing = false;

    private Bitmap bitmap;
    private Canvas canvas;


    public DrawView(Context context) {
        super(context);
        setFocusable(true);
        paint.setAntiAlias(true);
        paint.setARGB(255, 255, 255, 255);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.RGB_565);
        canvas = new Canvas(bitmap);
        if (bitmap != null) {
            canvas.drawBitmap(bitmap, 0, 0, null);
        }
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (bitmap != null) {
            canvas.drawBitmap(bitmap, 0, 0, null);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            drawPoint(event.getX(), event.getY(), event.getPressure());
            return true;
        }
        else if (event.getAction() == MotionEvent.ACTION_UP) {
            drawing = false;
            return true;
        }
        else if (event.getAction() == MotionEvent.ACTION_MOVE) {
            drawing = true;
            drawPoint(event.getX(), event.getY(), event.getPressure());
            return true;
        }

        return false;
    }

    private void drawPoint(float x, float y, float pressure) {
        if (bitmap != null) {
            //int pressureLevel = (int)(pressure * 255);
            //paint.setARGB(pressureLevel, 255, 255, 255);
            paint.setARGB(255, 255, 255, 255);
            if (drawing) {
                canvas.drawLine(lastX, lastY, x, y, paint);
            }
            else {
                canvas.drawPoint(x, y, paint);
            }

            lastX = x;
            lastY = y;

            invalidate();
        }
    }

    public void clearBitmap() {
        if (canvas != null) {
            paint.setARGB(0xff, 0, 0, 0);
            canvas.drawPaint(paint);
            invalidate();
        }
    }

    public void saveBitmap() {
        if (bitmap != null) {
            try {
                String path = Environment.getExternalStorageDirectory() + "/";
                File dir = new File(path);
                if (!dir.exists() || !dir.isDirectory()) {
                    dir.mkdirs();
                }
                File file = new File(Environment.getExternalStorageDirectory(), "/PDF-Moeller/" +  "signature.png");
                FileOutputStream fos;
                fos = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void loadBitmap() {
        String path = Environment.getExternalStorageDirectory() + "/PDF-Moeller/signature.png";
        Bitmap bmp = BitmapFactory.decodeFile(path);
        if (bmp != null) {
            if (canvas != null) {
                canvas.drawBitmap(bmp, 0, 0, null);
            }
            invalidate();
        }
    }
}