package dev.gohard.youtubemanager.util;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class YoutubeChannelResponse {
    private List<YoutubeChannel> items;

    @JsonIgnoreProperties(ignoreUnknown = true)
    @Data
    public static class YoutubeChannel {
        private String kind;
        private String etag;
        private String id;
        private Snippet snippet;
        private ContentDetails contentDetails;
        private Statistics statistics;
        private TopicDetails topicDetails;
        private Status status;
        private BrandingSettings brandingSettings;
        private InvideoPromotion invideoPromotion;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    @Data
    public static class Snippet {
        private String title;
        private String description;
        private String publishedAt;
        private Thumbnails thumbnails;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    @Data
    public static class Thumbnails {
        private ThumbnailInfo defaultThumbnail;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    @Data
    public static class ThumbnailInfo {
        private String url;
        private int width;
        private int height;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    @Data
    public static class ContentDetails {
        private RelatedPlaylists relatedPlaylists;
        private String googlePlusUserId;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    @Data
    public static class RelatedPlaylists {
        private String likes;
        private String favorites;
        private String uploads;
        private String watchHistory;
        private String watchLater;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    @Data
    public static class Statistics {
        private long viewCount;
        private long commentCount;
        private long subscriberCount;
        private boolean hiddenSubscriberCount;
        private long videoCount;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    @Data
    public static class TopicDetails {
        private List<String> topicIds;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    @Data
    public static class Status {
        private String privacyStatus;
        private boolean isLinked;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    @Data
    public static class BrandingSettings {
        private ChannelSettings channel;
        private WatchSettings watch;
        private ImageSettings image;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    @Data
    public static class ChannelSettings {
        private String title;
        private String description;
        private String keywords;
        private String defaultTab;
        private String trackingAnalyticsAccountId;
        private boolean moderateComments;
        private boolean showRelatedChannels;
        private boolean showBrowseView;
        private String featuredChannelsTitle;
        private List<String> featuredChannelsUrls;
        private String unsubscribedTrailer;
        private String profileColor;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    @Data
    public static class WatchSettings {
        private String textColor;
        private String backgroundColor;
        private String featuredPlaylistId;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    @Data
    public static class ImageSettings {
        private String bannerImageUrl;
        private String bannerMobileImageUrl;
        private BackgroundImageUrl backgroundImageUrl;
        private BannerImage largeBrandedBannerImage;
        private BannerImage smallBrandedBannerImage;
        private String watchIconImageUrl;
        private String trackingImageUrl;
        private String bannerTabletLowImageUrl;
        private String bannerTabletImageUrl;
        private String bannerTabletHdImageUrl;
        private String bannerTabletExtraHdImageUrl;
        private String bannerMobileLowImageUrl;
        private String bannerMobileMediumHdImageUrl;
        private String bannerMobileHdImageUrl;
        private String bannerMobileExtraHdImageUrl;
        private String bannerTvImageUrl;
        private String bannerExternalUrl;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    @Data
    public static class BackgroundImageUrl {
        private String defaultValue;
        private List<LocalizedBackgroundImage> localized;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    @Data
    public static class LocalizedBackgroundImage {
        private String value;
        private String language;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    @Data
    public static class BannerImage {
        private String defaultValue;
        private List<LocalizedBannerImage> localized;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    @Data
    public static class LocalizedBannerImage {
        private String value;
        private String language;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    @Data
    public static class InvideoPromotion {
        private Timing defaultTiming;
        private Position position;
        private List<InvideoPromotionItem> items;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    @Data
    public static class Timing {
        private String type;
        private long offsetMs;
        private long durationMs;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    @Data
    public static class Position {
        private String type;
        private String cornerPosition;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    @Data
    public static class InvideoPromotionItem {
        private Id id;
        private Timing timing;
        private String customMessage;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    @Data
    public static class Id {
        private String type;
        private String videoId;
        private String websiteUrl;
    }
}
