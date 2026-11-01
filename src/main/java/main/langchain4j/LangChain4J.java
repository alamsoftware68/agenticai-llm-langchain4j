package main.langchain4j;

import dev.langchain4j.data.message.SystemMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.service.AiServices;

public class LangChain4J {
    public static void main(String[] args) {
        //Telling AI what to answer, restricting AI to answer only particular area questions
        //Commenting below line to practice that we can use interface to restrict AI
        //below SystemMessage and UserMessage handled by Interface
        SystemMessage systemMessage = SystemMessage.systemMessage(""
            + "Act like Java trainer and answer only Java related queries " +
                "if it's out of Java and software please deny and tell" +
                "I cannot answer your query as I am only into Java and tech");

        UserMessage userQuestion = UserMessage.userMessage("Tell me about the Fifa worldcup");

        ChatModel model = OpenAiChatModel.builder()
                .apiKey(System.getenv("OPENAI_API_KEY"))
                .modelName("gpt-4o-mini")
                .maxTokens(20)
                .build();

        System.out.println("only answer");
        String answer = model.chat("Tell me who is the founder of Apple");
        System.out.println(answer);

        System.out.println("answer with metadata");

        ChatResponse response = model.chat(systemMessage, userQuestion);
        System.out.println(response.aiMessage().text());
        System.out.println("input token count" +response.tokenUsage().inputTokenCount());
        System.out.println("output token count" +response.tokenUsage().outputTokenCount());
        System.out.println("total token count" +response.tokenUsage().totalTokenCount());

        //Below interface we are not implementing, LangChain4J will implement interface DemoSupportInterface
        //All the interface implementation will be taken care by Langchain4J
        DemoSupportInterface support = AiServices.create(DemoSupportInterface.class, model);
        String answer1 = support.explain("Explain Java Dedendency Injection");
        System.out.println(answer1);
    }
}
