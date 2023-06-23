package dev.gohard.youtubemanager.util;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class YoutubePlaylistItemResponse {
    private List<YoutubePlaylistItem> items;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class YoutubePlaylistItem {
        private String etag;
        private String id;
        private Snippet snippet;
        private ContentDetails contentDetails;
        private Status status;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Snippet {
        private Date publishedAt;
        private String channelId;
        private String title;
        private String description;
        private Thumbnails thumbnails;
        private String channelTitle;
        private String playlistId;
        private int position;
        private ResourceId resourceId;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Thumbnails {
        @JsonProperty("default")
        private Thumbnail defaultThumbnail;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Thumbnail {
        private String url;
        private int width;
        private int height;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ResourceId {
        private String kind;
        private String videoId;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ContentDetails {
        private String videoId;
        private String startAt;
        private String endAt;
        private String note;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Status {
        private String privacyStatus;
    }
}

