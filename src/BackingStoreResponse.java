public class BackingStoreResponse {

    private int data;
    private float time;

    public BackingStoreResponse(int data,  float responseTime) {
        this.data = data;
        this.time = responseTime;
    }

    public int getData() {
        return data;
    }

    public float getResponseTime() {
        return this.time;
    }

}
