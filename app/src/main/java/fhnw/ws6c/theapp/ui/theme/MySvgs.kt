package fhnw.ws6c.theapp.ui.theme

import android.graphics.drawable.Drawable
import fhnw.ws6c.R

enum class MySvgs(val light_svg : Int, val dark_svg : Int){
    Circle(                 R.drawable.ic_circle_lm,        R.drawable.ic_circle_dm),
    Microphone(             R.drawable.ic_mic_lm,           R.drawable.ic_mic_dm),
    MicOn(                  R.drawable.ic_micro_on_lm,      R.drawable.ic_micro_on_dm),
    MicOff(                 R.drawable.ic_micro_off_lm,     R.drawable.ic_micro_off_dm),
    StarFilled(             R.drawable.ic_star_filled_lm,   R.drawable.ic_star_filled_dm),
    StarUnfilled(           R.drawable.ic_star_unfilled_lm, R.drawable.ic_star_unfilled_dm),
    StarFilled_dr_compl(    R.drawable.ic_star_filled_lm_drink_completed_screen,   R.drawable.ic_star_filled_dm),
    StarUnfilled_dr_compl(  R.drawable.ic_star_unfilled_lm_drink_completed_screen, R.drawable.ic_star_unfilled_dm),
    Swipe(                  R.drawable.ic_swipe_lm,         R.drawable.ic_swipe_dm),
    FilledStepCircle(       R.drawable.ic_step_circle_filled_lm, R.drawable.ic_step_circle_filled_dm),
    Logo(                   R.drawable.ic_logo_lm,          R.drawable.ic_logo_dm)
}