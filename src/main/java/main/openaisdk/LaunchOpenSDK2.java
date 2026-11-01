package main.openaisdk;

import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.models.chat.completions.ChatCompletionCreateParams;

public class LaunchOpenSDK2 {
    static OpenAIClient client = OpenAIOkHttpClient.fromEnv(); //it will load api key automatically

    //temperature : 0.0 constant answer, 1.0 random and dynamic message/output
    public static void main(String[] args) {
        String question= "suggest me java ai course names for live batch";
        System.out.println("Temperator value 0.0");
        for(int i=0;i<3;i++){
            System.out.println("call number"+i+" "+withTemperature(question, 0.0));
        }

        System.out.println("Temperator value 0.8");
        for(int i=0;i<3;i++){
            System.out.println("call number"+i+" "+withTemperature(question, 0.8));
        }
    }

    public static String withTemperature(String question, double temperature){
        ChatCompletionCreateParams params = ChatCompletionCreateParams.builder()
                .model("gpt-5.4-mini")
                .addSystemMessage("Act like java and AI tech expert and answer queries in 1 line and don't answer anything other than tech")
                .addUserMessage(question)
                .temperature(temperature)
                .maxCompletionTokens(25)
                .build();

        return client.chat()
                .completions()
                .create(params)
                .choices()
                .get(0)
                .message()
                .content()
                .orElse("");
    }
}
