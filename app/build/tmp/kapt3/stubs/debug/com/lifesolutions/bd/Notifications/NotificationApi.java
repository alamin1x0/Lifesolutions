package com.lifesolutions.bd.Notifications;

import java.lang.System;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J!\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0007\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\b"}, d2 = {"Lcom/lifesolutions/bd/Notifications/NotificationApi;", "", "postNotification", "Lretrofit2/Response;", "Lokhttp3/ResponseBody;", "notification", "Lcom/lifesolutions/bd/Notifications/Sender;", "(Lcom/lifesolutions/bd/Notifications/Sender;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public abstract interface NotificationApi {
    
    @org.jetbrains.annotations.Nullable()
    @retrofit2.http.POST(value = "fcm/send")
    @retrofit2.http.Headers(value = {"Authorization: key=AAAAiBAnWwI:APA91bE7dgTScT6AdXpTWjn_1kARyVsl4zzvSh_ziA1MZ6-GfhIhU7-B1wKyRJYSVTwQFRoUMhyDTbElUetc2wgFJb95HJXUa8KoiCwUxVWVXYmrrKDC1tLSr60mjREb902iw7er9f96", "Content-Type:application/json"})
    public abstract java.lang.Object postNotification(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Body()
    com.lifesolutions.bd.Notifications.Sender notification, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<okhttp3.ResponseBody>> continuation);
}