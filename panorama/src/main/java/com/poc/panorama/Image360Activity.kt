package com.poc.panorama

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.webkit.WebViewAssetLoader

class Image360Activity : AppCompatActivity() {

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val webView = WebView(this)
        setContentView(webView)

        // Configura o WebViewAssetLoader para carregar arquivos da pasta assets
        val assetLoader = WebViewAssetLoader.Builder()
            .addPathHandler("/assets/", WebViewAssetLoader.AssetsPathHandler(this))
            .build()

        webView.webViewClient = object : WebViewClient() {
            override fun shouldInterceptRequest(
                view: WebView,
                request: WebResourceRequest
            ): WebResourceResponse? {
                return assetLoader.shouldInterceptRequest(request.url)
            }
        }

        webView.settings.javaScriptEnabled = true
        webView.settings.domStorageEnabled = true  // Habilita armazenamento DOM (requerido por algumas interações JS)
        webView.settings.allowFileAccess = true
        webView.settings.useWideViewPort = true  // Para garantir o ajuste correto da cena no WebView
        webView.settings.loadWithOverviewMode = true

        // Carregar o arquivo HTML usando o assetLoader
        webView.loadUrl("https://appassets.androidplatform.net/assets/panorama.html")
    }
}
