# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile


-dontusemixedcaseclassnames 
-dontskipnonpubliclibraryclasses 
-verbose 

-dontoptimize 
-dontpreverify 

-keepattributes *Annotation* 
-keep public class com.google.vending.licensing.ILicensingService 
-keep public class com.android.vending.licensing.ILicensingService 

-keepclasseswithmembernames class * { 
    native <methods>; 
} 

-keepclassmembers public class * extends android.view.View { 
   void set*(***); 
   *** get*(); 
} 

-keepclassmembers class * extends android.app.Activity { 
   public void *(android.view.View); 
} 

-keepclassmembers enum * { 
    public static **[] values(); 
    public static ** valueOf(java.lang.String); 
} 

-keep class * implements android.os.Parcelable { 
  public static final android.os.Parcelable$Creator *; 
} 

-keepclassmembers class **.R$* { 
    public static <fields>; 
} 

-dontwarn android.support.**

# Add this global rule
-keepattributes Signature

# This rule will properly ProGuard all the model classes in
# the package com.yourcompany.models. Modify to fit the structure
# of your app.
-keepclassmembers class com.yourcompany.models.** {
  *;
}
