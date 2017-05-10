package in.co.tpsolutions.myapplication;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.ImageLoader;

/**
 * Created by Anurag on 3/26/2016.
 */
public class bmCustomVolleyRequest {

    private static bmCustomVolleyRequest customVolleyRequest;
    private static Context context;
    private RequestQueue requestQueue;

    private bmCustomVolleyRequest(Context context) {
        this.context = context;
        this.requestQueue = getRequestQueue();

    }

    public static synchronized bmCustomVolleyRequest getInstance(Context context) {
        if (customVolleyRequest == null) {
            customVolleyRequest = new bmCustomVolleyRequest(context);
        }
        return customVolleyRequest;
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            Cache cache = new DiskBasedCache(context.getCacheDir(), 10 * 1024 * 1024);
            Network network = new BasicNetwork(new HurlStack());
            requestQueue = new RequestQueue(cache, network);
            requestQueue.start();
        }
        return requestQueue;
    }
}
