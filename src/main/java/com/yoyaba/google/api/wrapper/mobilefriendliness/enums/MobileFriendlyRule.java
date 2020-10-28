package com.yoyaba.google.api.wrapper.mobilefriendliness.enums;

public enum MobileFriendlyRule {
    MOBILE_FRIENDLY_RULE_UNSPECIFIED, // Unknown rule. Sorry, we don't have any description for the rule that was broken.
    USES_INCOMPATIBLE_PLUGINS, //  	Plugins incompatible with mobile devices are being used.
    CONFIGURE_VIEWPORT, // Viewport is not specified using the meta viewport tag.
    FIXED_WIDTH_VIEWPORT, // Viewport defined to a fixed width.
    SIZE_CONTENT_TO_VIEWPORT, // Content not sized to viewport.
    USE_LEGIBLE_FONT_SIZES, // Font size is too small for easy reading on a small screen.
    TAP_TARGETS_TOO_CLOSE, // Touch elements are too close to each other.

}
