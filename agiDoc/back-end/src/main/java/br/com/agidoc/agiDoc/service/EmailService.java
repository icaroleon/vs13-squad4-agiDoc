package br.com.agidoc.agiDoc.service;

import br.com.agidoc.agiDoc.exception.RegraDeNegocioException;
import br.com.agidoc.agiDoc.model.company.Company;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class EmailService {
    private final freemarker.template.Configuration fmConfiguration;

    @Value("${spring.mail.username}")
    private String from;

    private final JavaMailSender emailSender;

    public void sendEmail(Object object, Integer identificadorTemplate) throws Exception {
        MimeMessage mimeMessage = emailSender.createMimeMessage();
        String subject = "Aviso";
        if(identificadorTemplate == 2){
            subject = "Dados alterados";
        }
        try {

            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

            mimeMessageHelper.setFrom(from);
//            mimeMessageHelper.setTo(object.getContact().getEmail());
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(getContentFromTemplate(object, identificadorTemplate), true);

            emailSender.send(mimeMessageHelper.getMimeMessage());
        } catch (MessagingException | IOException | TemplateException e) {
            throw new RegraDeNegocioException(e.getMessage() + "\nNão foi possivel enviar o email.");
        }
    }

    public String getContentFromTemplate(Object object, Integer identificadorTemplate) throws IOException, TemplateException {
        Map<String, Object> dados = new HashMap<>();


        dados.put("nome", ("teste"));
        dados.put("email", (Object) from);
        String html = null;
        if(identificadorTemplate == 1){
            Template templateCreate = fmConfiguration.getTemplate("email-template-institution-create.ftl");
            html = FreeMarkerTemplateUtils.processTemplateIntoString(templateCreate, dados);
        }
        else if(identificadorTemplate == 2){
            Template templateEdit = fmConfiguration.getTemplate("email-template-institution-edit.ftl");
            html = FreeMarkerTemplateUtils.processTemplateIntoString(templateEdit, dados);
        }
        else if(identificadorTemplate == 3){
            Template templateDelete = fmConfiguration.getTemplate("email-template-institution-delete.ftl");
            html = FreeMarkerTemplateUtils.processTemplateIntoString(templateDelete, dados);
        }
        return html;
    }
}
