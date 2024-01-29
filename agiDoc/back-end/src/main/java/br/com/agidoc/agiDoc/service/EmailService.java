package br.com.agidoc.agiDoc.service;
import br.com.agidoc.agiDoc.dto.document.DocumentDTO;
import br.com.agidoc.agiDoc.dto.process.ProcessDTO;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class EmailService {

    private final Configuration fmConfiguration;
    private final JavaMailSender emailSender;
    @Value("${spring.mail.username}")
    private String from;
    private String to = "icaro.leon@dbccompany.com.br";

    public void sendSimpleMessage() {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(from);
        message.setTo(to);
        message.setSubject("Assunto TESTE");
        message.setText("Meu e-mail!");
        emailSender.send(message);
    }

    public void sendWithAttachment() throws Exception {
        MimeMessage message = emailSender.createMimeMessage();

        MimeMessageHelper mimeMessageHelper = null;
        try {
            mimeMessageHelper = new MimeMessageHelper(message,
                    true);
        } catch (MessagingException e) {
            throw new Exception(e.getMessage());
        }

        mimeMessageHelper.setFrom(from);
        mimeMessageHelper.setTo(to);
        mimeMessageHelper.setSubject("Assunto 1");
        mimeMessageHelper.setText("Meu e-mail!");

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("static/imagem.jpg").getFile());
        FileSystemResource fileRs = new FileSystemResource(file);
        mimeMessageHelper.addAttachment(file.getName(), fileRs);

        System.out.println("File: " + file.getPath());

        emailSender.send(message);
    }

    public void sendEmail(Object object, String methodCaller) throws Exception {
        MimeMessage mimeMessage = emailSender.createMimeMessage();
        try {

            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

            mimeMessageHelper.setFrom(from);
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setSubject("Template" + methodCaller);
            mimeMessageHelper.setText(geContentFromTemplate(object, methodCaller), true);

            emailSender.send(mimeMessageHelper.getMimeMessage());
        } catch (MessagingException | IOException | TemplateException e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
    }

    public <T> String geContentFromTemplate(T t, String methodCaller) throws IOException, TemplateException {
        Map<String, Object> dados = new HashMap<>();
        Template template = null;

        if (methodCaller.equals("createDocument") || methodCaller.equals("updateEndereco")) {
            DocumentDTO documentDTO = (DocumentDTO) t;
            dados.put(("idDocument"), documentDTO.getId());
            dados.put("protocol", documentDTO.getProtocol());
            dados.put("expirationDate", documentDTO.getExpirationDate());
            dados.put("file", documentDTO.getFile());
            dados.put("associated", documentDTO.getAssociated());
            dados.put("processNumber", documentDTO.getProcessDTO().getProcessNumber());
            dados.put("title", documentDTO.getProcessDTO().getTitle());
            dados.put("description", documentDTO.getProcessDTO().getDescription());
            dados.put("processStatus", documentDTO.getProcessDTO().getProcessStatus());
            dados.put("institutionId", documentDTO.getProcessDTO().getInstitutionId());
            dados.put("email", from);
            template = fmConfiguration.getTemplate("email-template-" + methodCaller + ".ftl");
        }

        if (methodCaller.equals("deleteEndereco")) {
            Integer idEndereco = (Integer) t;
            dados.put("idEndereco", idEndereco);
            dados.put("email", from);
            template = fmConfiguration.getTemplate("email-template-" + methodCaller + ".ftl");
        }

        if (methodCaller.equals("createProcess") || methodCaller.equals("updateEndereco")) {
            ProcessDTO processDTO = (ProcessDTO) t;
            dados.put("processNumber", processDTO.getProcessNumber());
            dados.put("title", processDTO.getTitle());
            dados.put("description", processDTO.getDescription());
            dados.put("processStatus", processDTO.getProcessStatus());
            dados.put("institutionId", processDTO.getInstitutionId());

        }

            String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, dados);
        return html;
    }
}