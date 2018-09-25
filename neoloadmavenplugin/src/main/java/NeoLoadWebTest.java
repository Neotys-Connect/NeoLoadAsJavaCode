public class NeoLoadWebTest {
    private String testid;
    private String trendingurl;
    private String testurl;

    public NeoLoadWebTest(String testid, String trendingurl, String testurl) {
        this.testid = testid;
        this.trendingurl = trendingurl;
        this.testurl = testurl;
    }

    public String getTestid() {
        return testid;
    }

    public String getTrendingurl() {
        return trendingurl;
    }

    public String getTesturl() {
        return testurl;
    }
}
