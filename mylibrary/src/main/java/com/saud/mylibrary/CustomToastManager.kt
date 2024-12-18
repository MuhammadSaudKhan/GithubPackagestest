package com.saud.mylibrary

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast

class CustomToastManager(private val context: Context) {

    /**
     * Displays a custom Toast message.
     *
     * @param message The text message to display.
     * @param layoutId The resource ID of the custom layout for the Toast.
     * @param duration The duration for which the Toast is displayed. Use Toast.LENGTH_SHORT or Toast.LENGTH_LONG.
     * @param gravity The position of the Toast on the screen. Default is Gravity.BOTTOM.
     * @param xOffset Horizontal offset from the gravity position. Default is 0.
     * @param yOffset Vertical offset from the gravity position. Default is 0.
     */
    fun showCustomToast(
        message: String,
        layoutId: Int,
        duration: Int = Toast.LENGTH_SHORT,
        gravity: Int = Gravity.BOTTOM,
        xOffset: Int = 0,
        yOffset: Int = 0
    ) {
        val inflater = LayoutInflater.from(context)
        val customView: View = inflater.inflate(layoutId, null)

        // Set the message on the custom view
        val messageTextView: TextView? = customView.findViewById(R.id.toast_message)
        messageTextView?.text = message

        // Create and configure the Toast
        val toast = Toast(context)
        toast.view = customView
        toast.duration = duration
        toast.setGravity(gravity, xOffset, yOffset)
        toast.show()
    }

    /**
     * Displays a default styled Toast message.
     *
     * @param message The text message to display.
     * @param duration The duration for which the Toast is displayed. Use Toast.LENGTH_SHORT or Toast.LENGTH_LONG.
     */
    fun showDefaultToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(context, message, duration).show()
    }
}
