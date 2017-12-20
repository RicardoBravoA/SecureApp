#include <jni.h>
#include <string>

/**
 * Created by Ricardo Bravo on 14/12/2017.
 */

extern "C" {

    JNIEXPORT jstring JNICALL
    Java_com_rba_secureapp_util_DeviceUtil_getPackageName(
            JNIEnv *env,
            jobject /* this */) {
        std::string value = "com.rba.secureapp";
        return env->NewStringUTF(value.c_str());
    }

    JNIEXPORT jstring JNICALL
    Java_com_rba_secureapp_util_DeviceUtil_getGooglePackageName(
            JNIEnv *env,
            jobject /* this */) {
        std::string value = "com.android.vending";
        return env->NewStringUTF(value.c_str());
    }

    JNIEXPORT jstring JNICALL
    Java_com_rba_secureapp_util_DeviceUtil_getHash(JNIEnv *env,
                                                   jobject /* this */) {
        std::string value = "80232019";
        return env->NewStringUTF(value.c_str());
    }

}