package com.example.crediscountgo;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.example.crediscountgo.model.MovieDiscount;
import com.example.crediscountgo.model.Surprise;
import com.google.ar.core.Anchor;
import com.google.ar.core.Frame;
import com.google.ar.core.HitResult;
import com.google.ar.core.Plane;
import com.google.ar.core.Trackable;
import com.google.ar.core.TrackingState;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.HitTestResult;
import com.google.ar.sceneform.Node;
import com.google.ar.sceneform.math.Vector3;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.rendering.ViewRenderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.gson.Gson;

import java.util.List;

public class ARActivity extends AppCompatActivity {
    private static final String TAG = ARActivity.class.getSimpleName();
    private static final double MIN_OPENGL_VERSION = 3.1;

    private ArFragment arFragment;
    private ModelRenderable chestRenderable;
    private ModelRenderable surpriseRenderable;
    private ViewRenderable tipsRenderable, congratsRenerable;
    private boolean isInit = false;
    private Surprise surprise;

    @Override
    @SuppressWarnings({"AndroidApiChecker", "FutureReturnValueIgnored"})
    // CompletableFuture requires api level 24
    // FutureReturnValueIgnored is not valid
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!checkIsSupportedDeviceOrFinish(this)) {
            return;
        }

        setContentView(R.layout.activity_ar);
        arFragment = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.ux_fragment);



        // When you build a Renderable, Sceneform loads its resources in the background while returning
        // a CompletableFuture. Call thenAccept(), handle(), or check isDone() before calling get().
        ModelRenderable.builder()
                .setSource(this, Uri.parse("model.sfb"))
                .build()
                .thenAccept(renderable -> surpriseRenderable = renderable)
                .exceptionally(
                        throwable -> {
                            Toast toast =
                                    Toast.makeText(this, "Unable to load andy renderable", Toast.LENGTH_LONG);
                            toast.setGravity(Gravity.CENTER, 0, 0);
                            toast.show();
                            return null;
                        });

        ModelRenderable.builder()
                .setSource(this, Uri.parse("NOVELO_CHEST.sfb"))
                .build()
                .thenAccept(renderable -> chestRenderable = renderable)
                .exceptionally(
                        throwable -> {
                            Toast toast =
                                    Toast.makeText(this, "Unable to load andy renderable", Toast.LENGTH_LONG);
                            toast.setGravity(Gravity.CENTER, 0, 0);
                            toast.show();
                            return null;
                        });

        ViewRenderable.builder()
                .setView(this, R.layout.treasure_box_tips)
                .build()
                .thenAccept(renderable -> tipsRenderable = renderable);
        ViewRenderable.builder()
                .setView(this, R.layout.congrats_pop_up)
                .build()
                .thenAccept(renderable -> congratsRenerable = renderable);

        /**
         arFragment.setOnTapArPlaneListener(
         (HitResult hitResult, Plane plane, MotionEvent motionEvent) -> {
         if (andyRenderable == null) {
         return;
         }

         // Create the Anchor.
         Anchor anchor = hitResult.createAnchor();
         AnchorNode anchorNode = new AnchorNode(anchor);
         anchorNode.setParent(arFragment.getArSceneView().getScene());

         // Create the transformable andy and add it to the anchor.
         TransformableNode andy = new TransformableNode(arFragment.getTransformationSystem());
         andy.setParent(anchorNode);
         andy.setRenderable(andyRenderable);
         andy.select();
         });

         **/


        // Set an update listener on the Scene that will hide the loading message once a Plane is
        // detected.
        arFragment.getArSceneView()
                .getScene()
                .setOnUpdateListener(
                        frameTime -> {

                            Frame frame = arFragment.getArSceneView()
                                    .getArFrame();
                            if (frame == null) {
                                return;
                            }

                            if (frame.getCamera().getTrackingState() != TrackingState.TRACKING) {
                                return;
                            }

                            for (Plane plane : frame.getUpdatedTrackables(Plane.class)) {
                                if (plane.getTrackingState() == TrackingState.TRACKING) {
                                    addInitObject();
                                }
                            }
                            arFragment.onUpdate(frameTime);
                        });

    }

    public Surprise createSurprise(){
        return new MovieDiscount();
    }

    @Override
    public void onResume(){
        super.onResume();
        //addInitObject();
    }

    public void addInitObject(){
        Frame frame = arFragment.getArSceneView().getArFrame();
        Point point = getScreenCenter();
        if(frame != null){
            List<HitResult> hits = frame.hitTest((float)point.x, (float) point.y);
            for (HitResult hit : hits){
                Trackable trackable = hit.getTrackable();
                if (!isInit){
                    if (chestRenderable == null) {
                        return;
                    }


                    Anchor anchor = hit.createAnchor();
                    AnchorNode anchorNode = new AnchorNode(anchor);
                    anchorNode.setParent(arFragment.getArSceneView().getScene());

                    // Create the Anchor.
                    Node base = new Node();
                    base.setParent(anchorNode);

                    Node ground = new Node();
                    ground.setParent(base);
                    ground.setLocalPosition(new Vector3(0.0f, 0.3f, 0.0f));

                    // Create the transformable andy and add it to the anchor.
                    Node chest = new Node();
                    chest.setParent(ground);
                    chest.setRenderable(chestRenderable);
                    chest.setLocalScale(new Vector3(1f, 1f, 1f));

                    Node tips = new Node();
                    tips.setParent(chest);
                    tips.setRenderable(tipsRenderable);
                    tips.setLocalPosition(new Vector3(0.0f, 0.3f, 0.0f));


                    chest.setOnTapListener(new Node.OnTapListener() {
                        @Override
                        public void onTap(HitTestResult hitTestResult, MotionEvent motionEvent) {
                            Log.d(TAG, "3d obj is tapped");
                            chest.removeChild(tips);
                            ground.removeChild(chest);

                            Node surpriseNode = new Node();
                            surpriseNode.setParent(ground);
                            surpriseNode.setRenderable(surpriseRenderable);
                            surpriseNode.setLocalScale(new Vector3(0.8f, 0.8f, 0.8f));
                            surpriseNode.setLocalPosition(new Vector3(0.0f, 0.18f, 0.0f));

                            surpriseNode.setOnTapListener(new Node.OnTapListener() {
                                @Override
                                public void onTap(HitTestResult hitTestResult, MotionEvent motionEvent) {
                                    Log.d(TAG, "surpriseNode is tapped");
                                    MovieDiscount discount = new MovieDiscount();
                                    Gson gson = new Gson();
                                    Intent startDiscountDts = new Intent(ARActivity.this, DiscountDetailsActivity.class);
                                    startDiscountDts.putExtra("surpriseDetails", gson.toJson(discount));
                                    startActivity(startDiscountDts);

                                }
                            });



                            Node tips = new Node();
                            tips.setParent(surpriseNode);
                            tips.setRenderable(congratsRenerable);
                            tips.setLocalPosition(new Vector3(0.0f, 0.3f, 0.0f));
                        }
                    });



                    isInit=true;
                }

            }
        }


    }

    public Point getScreenCenter(){
        View vw = findViewById(android.R.id.content);
        return new Point(vw.getWidth()/2, vw.getHeight()/2);
    }

    /**
     * Returns false and displays an error message if Sceneform can not run, true if Sceneform can run
     * on this device.
     *
     * <p>Sceneform requires Android N on the device as well as OpenGL 3.1 capabilities.
     *
     * <p>Finishes the activity if Sceneform can not run
     */
    public static boolean checkIsSupportedDeviceOrFinish(final Activity activity) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            Log.e(TAG, "Sceneform requires Android N or later");
            Toast.makeText(activity, "Sceneform requires Android N or later", Toast.LENGTH_LONG).show();
            activity.finish();
            return false;
        }
        String openGlVersionString =
                ((ActivityManager) activity.getSystemService(Context.ACTIVITY_SERVICE))
                        .getDeviceConfigurationInfo()
                        .getGlEsVersion();
        if (Double.parseDouble(openGlVersionString) < MIN_OPENGL_VERSION) {
            Log.e(TAG, "Sceneform requires OpenGL ES 3.1 later");
            Toast.makeText(activity, "Sceneform requires OpenGL ES 3.1 or later", Toast.LENGTH_LONG)
                    .show();
            activity.finish();
            return false;
        }
        return true;
    }

}