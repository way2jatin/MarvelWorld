# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /home/uw/Android/Sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

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
# Jackson
 -keepnames class com.fasterxml.jackson.** { *; }
  -dontwarn com.fasterxml.jackson.databind.**
  -dontwarn org.codehaus.jackson.**
  -dontwarn javax.xml.**
 -dontwarn javax.xml.stream.events.**
  -keep class org.codehaus.** { *; }
  -keepclassmembers public final enum org.codehaus.jackson.annotate.JsonAutoDetect$Visibility {
  public static final org.codehaus.jackson.annotate.JsonAutoDetect$Visibility *; }

 -keepclassmembers class * {
      @com.fasterxml.jackson.annotation.JsonCreator *;
      @com.fasterxml.jackson.annotation.JsonProperty *;
 }
 -keepnames class com.fasterxml.jackson.** { *; }

  -keep class com.jatin.marvelworld.model.** {
    public void set*(***);
    public *** get*();
  }

#ButterKnife
-keep class butterknife.*
-keepclasseswithmembernames class * { @butterknife.* <methods>; }
-keepclasseswithmembernames class * { @butterknife.* <fields>; }
