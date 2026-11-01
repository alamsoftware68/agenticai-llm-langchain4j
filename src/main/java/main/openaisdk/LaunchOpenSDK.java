package main.openaisdk;

import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.models.chat.completions.ChatCompletion;
import com.openai.models.chat.completions.ChatCompletionCreateParams;
import com.openai.models.responses.Response;
import com.openai.models.responses.ResponseCreateParams;

//OpenAISDK
public class LaunchOpenSDK {

    static OpenAIClient client = OpenAIOkHttpClient.fromEnv(); //it will load api key automatically

    public static void main(String[] args) {

//        String apiKey=System.getenv("OPENAI_API_KEY");
//        if(apiKey == null | apiKey.isBlank()){
//            System.out.println("API key not found please set api key");
//        }

        String systemMessage="You are expert, Reply in short one line only about Java and related tech. Non tech Deny";


        ChatCompletionCreateParams firstConversation = ChatCompletionCreateParams.builder()
                .model("gpt-5.4-mini")
                .addSystemMessage(systemMessage)
                .addUserMessage("Hi, My name is Alex")
                .build();
        String firstReply=ask(firstConversation);
        System.out.println("First call : "+ firstReply);
        System.out.println("***********************************************");

        ChatCompletionCreateParams secondtConversation = ChatCompletionCreateParams.builder()
                .model("gpt-5.4-mini")
                .addSystemMessage(systemMessage)
                .addUserMessage("What is my name")
                .build();
        String secondReply=ask(secondtConversation);
        System.out.println("Second call : "+ secondReply);
        System.out.println("***********************************************");

        ChatCompletionCreateParams thirdConversation = ChatCompletionCreateParams.builder()
                .model("gpt-5.4-mini")
                .addSystemMessage(systemMessage)
                //.addUserMessage("Hi my name is Alex")
                .addAssistantMessage(firstReply)
                .addUserMessage("what is my name")
                .build();
        String thirdReply=ask(thirdConversation);
        System.out.println("third call : "+ thirdReply);
        System.out.println("***********************************************");



//        OpenAIClient client = OpenAIOkHttpClient.builder()
//                .apiKey(apiKey)
//                .build();
//
//        ResponseCreateParams params =
//                ResponseCreateParams.builder()
//                        .input("Tell me about AI")
//                        .model("gpt-4o-mini")
//                        .build();
//
//        Response response = client.responses().create(params);
//        //System.out.println(response);
//        response.output().stream()
//                .flatMap(item -> item.message().stream())
//                .flatMap(message -> message.content().stream())
//                .flatMap(content -> content.outputText().stream())
//                .forEach(outputText -> System.out.println(outputText.text()));
    }

    static String ask(ChatCompletionCreateParams chatParams){
        return client.chat()
                .completions()
                .create(chatParams)
                .choices()
                .get(0)
                .message()
                .content()
                .orElse("");
    }

}
