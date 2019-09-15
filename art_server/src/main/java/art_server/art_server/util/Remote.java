package art_server.art_server.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.Socket;

@Component
public class Remote {
    @Value("${remote-ip}")
    private String remoteIp;

    public Object remoteCall(String content){
        Socket socket=null;

        try {
            socket=new Socket(remoteIp,8700);
            OutputStream os = socket.getOutputStream();
            PrintStream out = new PrintStream(os);
            // 发送内容
            out.print(content);
            // 告诉服务进程，内容发送完毕，可以开始处理
            out.print("over");

            // 获取服务进程的输入流
            InputStream is = socket.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is,"utf-8"));
            String tmp = null;
            StringBuilder sb = new StringBuilder();
            // 读取内容
            while((tmp=br.readLine())!=null)
                sb.append(tmp).append('\n');
            // 解析结果
            return sb.toString();
        }catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {if(socket!=null) socket.close();} catch (IOException e) {}
        }
        return null;
    }

}
