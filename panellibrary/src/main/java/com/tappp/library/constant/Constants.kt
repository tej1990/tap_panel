package com.tappp.library.constant


/**
 * Created by Tejas A. Prajapati on 30/11/22.
 */
object Constants {

    /**
     * Flutter communication key
     */
    const val FLUTTER_ENGINE_ID = "demo"
    const val FLUTTER_CHANNEL = "demo/channel"

    const val MESSAGE = "MESSAGE"
    const val VALUE = "VALUE"

    /**
     * flutter methods
     */
    const val SEND_TO_FLUTTER = "SEND_TO_FLUTTER"
    const val GET_FROM_NATIVE = "GET_FROM_NATIVE"
    const val NATIVE_NOTIFICATION = "NATIVE_NOTIFICATION"


    const val IMAGE_VIEW = "IMAGE_VIEW"
    const val LOCAL_WEB_VIEW = "LOCAL_WEB_VIEW" //1
    const val S3_WEB_VIEW = "S3_WEB_VIEW"//2
    const val LIBRARY_CALENDER = "LIBRARY_CALENDER"//3
    const val LIBRARY_PANEL = "LIBRARY_PANEL"//5

    /*Events*/
    const val EVENT_RECEIVE_MESSAGE = "showAndroidToast"//5
    const val EVENT_PASSING_WEB = "getMessageFromAndroid"//5

}