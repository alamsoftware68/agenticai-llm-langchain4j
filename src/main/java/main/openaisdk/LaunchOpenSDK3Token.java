package main.openaisdk;

import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.models.chat.completions.ChatCompletion;
import com.openai.models.chat.completions.ChatCompletionCreateParams;
import com.openai.models.completions.CompletionUsage;

//Below example is with respect token cost
public class LaunchOpenSDK3Token {

    public static void main(String[] args) {

        OpenAIClient client = OpenAIOkHttpClient.fromEnv(); //it will load api key automatically

        ChatCompletionCreateParams params = ChatCompletionCreateParams.builder()
                .model("gpt-5.4-mini")
                .addSystemMessage("Act like java and AI tech expert and answer queries in 1 line and don't answer anything other than tech")
                .addUserMessage("Explain dependency injection in 2 lines")
                .build();

        //below code is responsible to filter the output and give the text which is default in SpringAI
        ChatCompletion response = client.chat().completions().create(params);
        System.out.println(response.choices().get(0).message().content().orElse(""));

        System.out.println("mode : "+response.model());

        CompletionUsage usage = response.usage().orElseThrow();
        System.out.println("prompt/input token used : "+usage.promptTokens());
        System.out.println("output token used : "+usage.completionTokens());
        System.out.println("total token used : "+usage.totalTokens());
    }

}
