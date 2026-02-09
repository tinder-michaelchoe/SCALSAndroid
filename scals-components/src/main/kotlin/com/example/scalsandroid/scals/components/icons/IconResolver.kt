package com.example.scalsandroid.scals.components.icons

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.filled.Help
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Resolves string-based icon identifiers to Material Icons.
 * Provides mappings from cross-platform icon names to Android Material Icons.
 */
object IconResolver {
    /**
     * Resolves an icon identifier to a Material Icon ImageVector.
     * Returns null if the identifier is not recognized.
     */
    fun resolve(identifier: String?): ImageVector? {
        if (identifier == null) return null
        return iconMap[identifier]
    }

    private val iconMap: Map<String, ImageVector> = mapOf(
        // Action Icons
        "Add" to Icons.Filled.Add,
        "Delete" to Icons.Filled.Delete,
        "DeleteFilled" to Icons.Filled.Delete,
        "Close" to Icons.Filled.Close,
        "CancelFilled" to Icons.Filled.Cancel,
        "Check" to Icons.Filled.Check,
        "CheckCircleFilled" to Icons.Filled.CheckCircle,
        "Refresh" to Icons.Filled.Refresh,
        "Share" to Icons.Filled.Share,

        // Navigation Icons
        "ChevronRight" to Icons.AutoMirrored.Filled.KeyboardArrowRight,
        "ArrowForward" to Icons.AutoMirrored.Filled.ArrowForward,
        "OpenExternal" to Icons.Filled.NorthEast,
        "Upload" to Icons.Filled.Upload,
        "Download" to Icons.Filled.Download,

        // Rating/Favorites
        "Star" to Icons.Filled.StarBorder,
        "StarFilled" to Icons.Filled.Star,
        "Favorite" to Icons.Filled.FavoriteBorder,
        "FavoriteFilled" to Icons.Filled.Favorite,
        "ThumbUp" to Icons.Filled.ThumbUp,

        // Common UI
        "Settings" to Icons.Filled.Settings,
        "MoreVert" to Icons.Filled.MoreVert,
        "Bookmark" to Icons.Filled.BookmarkBorder,
        "Search" to Icons.Filled.Search,
        "HomeFilled" to Icons.Filled.Home,
        "PersonFilled" to Icons.Filled.Person,
        "AccountCircle" to Icons.Filled.AccountCircle,
        "AccountCircleFilled" to Icons.Filled.AccountCircle,
        "People" to Icons.Filled.People,
        "NotificationsFilled" to Icons.Filled.Notifications,
        "NotificationsActive" to Icons.Filled.NotificationsActive,
        "LockFilled" to Icons.Filled.Lock,

        // Content Types
        "Image" to Icons.Filled.Image,
        "ImageFilled" to Icons.Filled.Image,
        "VideoFilled" to Icons.Filled.VideoLibrary,
        "MusicNote" to Icons.Filled.MusicNote,
        "DocumentFilled" to Icons.Filled.Description,
        "ArticleFilled" to Icons.Filled.Article,
        "BookFilled" to Icons.Filled.Book,

        // Media Controls
        "PlayFilled" to Icons.Filled.PlayArrow,
        "SkipPrevious" to Icons.Filled.SkipPrevious,
        "SkipNext" to Icons.Filled.SkipNext,
        "Shuffle" to Icons.Filled.Shuffle,
        "Repeat" to Icons.Filled.Repeat,
        "VolumeUp" to Icons.AutoMirrored.Filled.VolumeUp,

        // Weather Icons (Extended)
        "Sunny" to Icons.Filled.WbSunny,
        "Cloudy" to Icons.Filled.WbCloudy,
        "Cloud" to Icons.Filled.Cloud,
        "Rain" to Icons.Filled.WaterDrop,
        "Humidity" to Icons.Filled.Opacity,
        "Wind" to Icons.Filled.Air,
        "Visibility" to Icons.Filled.RemoveRedEye,
        "Sunrise" to Icons.Filled.WbTwilight,
        "Sunset" to Icons.Filled.WbTwilight,

        // Device/Tech Icons
        "Phone" to Icons.Filled.PhoneAndroid,
        "Watch" to Icons.Filled.Watch,
        "Headset" to Icons.Filled.Headset,
        "Gaming" to Icons.Filled.SportsEsports,

        // Information/Alerts
        "InfoFilled" to Icons.Filled.Info,
        "HelpFilled" to Icons.AutoMirrored.Filled.Help,
        "Warning" to Icons.Filled.PanTool,

        // Other UI Elements
        "Archive" to Icons.Filled.Archive,
        "Brush" to Icons.Filled.Brush,
        "Trending" to Icons.Filled.Whatshot,
        "Sparkle" to Icons.Filled.AutoAwesome,
        "Globe" to Icons.Filled.Language,
        "Bolt" to Icons.Filled.Bolt,
        "Schedule" to Icons.Filled.Schedule,
        "ScheduleFilled" to Icons.Filled.Schedule,
        "Checklist" to Icons.Filled.Checklist,
        "AddCircleFilled" to Icons.Filled.AddCircle,
        "Edit" to Icons.Filled.Edit,
    )
}
