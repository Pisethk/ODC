package com.cambodge.piseth.opendevelopmentcambodia;

        import android.app.Activity;
        import android.app.DownloadManager;
        import android.net.Uri;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.KeyEvent;
        import android.view.View;
        import android.webkit.DownloadListener;
        import android.webkit.WebView;
        import android.webkit.WebViewClient;
        import android.app.DownloadManager.Request;
        import android.os.Environment;
        import android.content.Intent;

public class HomeActivity extends Activity {

    private WebView mWebView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mWebView =(WebView) findViewById(R.id.webView);
        mWebView.getSettings().setJavaScriptEnabled(true);
        //loading url to webveiw
        mWebView.loadUrl("https://cambodia.opendevelopmentmekong.net");
        //Keep content on webview client
        mWebView.setWebViewClient(new MyWebViewClient());
        mWebView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        //download listener
        mWebView.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength)
            {
               //download file with specifying rename
                /*Request request = new Request (Uri.parse(url));
                request.allowScanningByMediaScanner();
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, ".pdf");
                DownloadManager dm = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);

                dm.enqueue(request);*/
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });
    }
    private class MyWebViewClient extends WebViewClient
    {

        @Override
        public boolean shouldOverrideUrlLoading(WebView webView, String url)
        {

            webView.loadUrl(url);
            return true;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)

    {

        if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack())

        {
            mWebView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
