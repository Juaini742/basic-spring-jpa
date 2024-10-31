package com.core.jpatraining2.controller;


import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/public")
public class LearnResponseController {

    /**
     * ResponseEntity<T>:
     * Ini adalah pilihan yang paling fleksibel dan umum digunakan di Spring Boot.
     * ResponseEntity memungkinkan Anda mengatur respons dengan lebih mudah,
     * termasuk status code, header, dan body.
     */

    @GetMapping("/get-response-entity")
    public ResponseEntity<String> getResponseEntity() {
        return ResponseEntity.ok("Hello World");
    }

    // HttpServletResponse
    @GetMapping("/get-http-servlet-simple")
    public void simpleResponse(HttpServletResponse resp) throws IOException {
        resp.setContentType("text/plain");
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.getWriter().println("hello this is simple response from http servlet response");
    }

    @GetMapping("/get-http-servlet-simple-json")
    public void simpleResponseJson(HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.getWriter().println("hello this is simple response from http servlet response json");
    }

    @GetMapping("header-cache-control")
    public void headerCacheControl(HttpServletResponse resp) throws IOException {
        /**
         * Sets the HTTP response headers to prevent caching of the response.
         * This is useful for responses that should not be cached by the client or any intermediate proxies.
         * The headers set are:
         * - Cache-Control: no-cache, no-store, must-revalidate
         * - Pragma: no-cache
         * - Expires: 0
         * The response content type is set to "text/plain" and the response body is set to "hello this is cache control".
         */
        resp.setContentType("text/plain");
        resp.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        resp.setHeader("Pragma", "no-cache");
        resp.setHeader("Expires", "0");
        resp.getWriter().println("hello this is cache control");
    }

    @GetMapping("/header-cors-security")
    public void headerCorsSecurity(HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        resp.setHeader("Strict-Transport-Security", "max-age=31536000; includeSubDomains");
        resp.setHeader("X-Content-Type-Options", "nosniff");
        resp.setHeader("X-Frame-Options", "DENY");
        resp.setHeader("Content-Security-Policy", "default-src 'self'");
        resp.setHeader("X-XSS-Protection", "1; mode=block");
        resp.getWriter().println("Response with enhanced headers");
    }

    @GetMapping("/header-cors-header")
    public void headerCorsHeader(HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        resp.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
        resp.setHeader("Access-Control-Max-Age", "3600");
        resp.getWriter().println("Response with enhanced headers");
    }

    @GetMapping("/header-custom")
    public void headerCustomHeader(HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        resp.setHeader("X-Custom-Header-Name", "This is custom value");
        resp.getWriter().println("Response with custom header");
    }

    @GetMapping("/header-download")
    public void headerDownloadHeader(HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        resp.setHeader("Content-Disposition", "attachment; filename=header-custom-header");
        resp.getWriter().println("Response with the download file");
    }

    @GetMapping("/header-etag")
    public void headerEtagHeader(HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        String etag = "W/\"12345\"";
        resp.setHeader("ETag", etag);
        resp.getWriter().println("Response with Etag Headre");
    }

    @GetMapping("/header-rate-limiting")
    public void headerRateLimitingHeader(HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        resp.setHeader("X-RateLimit-Limit", "100");
        resp.setHeader("X-RateLimit-Remining", "50");
        resp.setHeader("X-RateLimit-Reset", "360");
        resp.getWriter().println("Response with rate limiting");
    }

    @GetMapping("/header-redirect-location")
    public void headerRedirectLocationHeader(HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        resp.setStatus(HttpServletResponse.SC_FOUND);
        resp.setHeader("Location", "https://www.google.com");
    }

    @GetMapping("/header-server-timing")
    public void headerServerTimingHeader(HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        resp.setHeader("Server-Timing", "app;dur=50, db;dur=23");
        resp.getWriter().println("Response with Server-Timing header");
    }

    @GetMapping("/set-cookie")
    public void setCookieHeader(HttpServletResponse resp) throws IOException {
        Cookie cookie = new Cookie("sessionId", "123456789");
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setMaxAge(60 * 60); // one hour
        resp.addCookie(cookie);
        resp.setContentType("application/json");
        resp.getWriter().println("Response with cookie header");
    }

    @GetMapping("/remove-cookie")
    public void removeCookieHeader(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (req.getCookies() != null) {
            for (Cookie cookie : req.getCookies()) {
                cookie.setMaxAge(0);
                cookie.setPath("/");
                resp.addCookie(cookie);
            }
        }

        resp.setContentType("application/json");
        resp.getWriter().println("Response with remove cookie header");
    }

    @GetMapping("/set-redirect")
    public void setRedirectHeader(HttpServletResponse resp) throws IOException {
        resp.sendRedirect("https://www.google.com");
    }

    @GetMapping("/not-found")
    public void notFound(HttpServletResponse resp) throws IOException {
        resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
        resp.getWriter().println("Not found");
    }

    @GetMapping("/json")
    public void ResponseJson(HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.getWriter().write("{\\\"message\\\": \\\"This is a JSON response\\\"}");
    }
}
