import com.fasterxml.jackson.annotation.JsonProperty;

public class Nasa {
    private final String Copyright;
    private final String date;
    private final String explanation;
    private final String hdurl;
    private final String media;
    private final String service;
    private final String title;
    private final String url;

    public Nasa(
            @JsonProperty ("copyright") String copyright,
            @JsonProperty ("date")String date,
            @JsonProperty ("explanation")String explanation,
            @JsonProperty ("hdurl")String hdurl,
            @JsonProperty ("media_type")String media,
            @JsonProperty ("service_version")String service,
            @JsonProperty ("title")String title,
            @JsonProperty ("url")String url) {
        this.Copyright = copyright;
        this.date = date;
        this.explanation = explanation;
        this.hdurl = hdurl;
        this.media = media;
        this.service = service;
        this.title = title;
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return "Nasa{" +
                 "Copyright='" + Copyright + '\'' +
                ", Date='" + date + '\'' +
                ", explanation='" + explanation + '\'' +
                ", hdurl='" + hdurl + '\'' +
                ", media='" + media + '\'' +
                ", service='" + service + '\'' +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
