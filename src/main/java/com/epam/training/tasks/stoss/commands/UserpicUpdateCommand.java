package com.epam.training.tasks.stoss.commands;

import com.epam.training.tasks.stoss.entities.User;
import com.epam.training.tasks.stoss.services.ServiceException;
import com.epam.training.tasks.stoss.services.UserService;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

import static com.epam.training.tasks.stoss.commands.Attributes.ERROR_MESSAGE_ATTRIBUTE;
import static com.epam.training.tasks.stoss.commands.Attributes.USER_ATTRIBUTE;

public class UserpicUpdateCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(UserpicUpdateCommand.class);

    private static final String PAGE = "controller?command=user";
    private static final int MAX_FILE_SIZE = 1000000;
    private static final int MAX_MEM_SIZE = 4096;
    private static final String RESOURCES_PATH = "D:/dev/Tomcat8.5/webapps/resources/stoss/";
    private static final String USERPIC_PATH = "../resources/stoss/pic/png/";
    private static final String PICTURES_PATH = "pic/png/";
    private static final String TEMP_PATH = RESOURCES_PATH + "temp/";

    private final UserService userService;

    public UserpicUpdateCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(MAX_MEM_SIZE);
        factory.setRepository(new File(TEMP_PATH));
        ServletFileUpload uploader = new ServletFileUpload(factory);
        uploader.setFileSizeMax(MAX_FILE_SIZE);
        try {
            List<FileItem> fileItems = uploader.parseRequest(request);
            FileItem userpicItem = fileItems.get(1);
            HttpSession session = request.getSession();
            if (userpicItem.getSize() > 0) {
                User user = (User) session.getAttribute(USER_ATTRIBUTE);
                int userHash = user.hashCode();
                String filename = System.currentTimeMillis() + "_" + userHash +  ".png";
                String path = RESOURCES_PATH + PICTURES_PATH + filename;
                File userpic = new File (path);
                InputStream inputStream = userpicItem.getInputStream();
                Files.copy(inputStream, userpic.toPath(), StandardCopyOption.REPLACE_EXISTING);

                FileItem idItem = fileItems.get(0);
                Long id = Long.valueOf(idItem.getString());
                Optional<User> optionalUser = userService.updateUserpic(id, USERPIC_PATH + filename);
                Long userId = user.getId();
                if (userId.equals(id)){
                    User updatedUser = optionalUser.get();
                    session.setAttribute(USER_ATTRIBUTE, updatedUser);
                }
            } else {
                session.setAttribute(ERROR_MESSAGE_ATTRIBUTE, "Please choose the file to upload!");
            }

            } catch (IOException | FileUploadException e) {
                LOGGER.error(e);
                throw new ServiceException(e);
            }
        return CommandResult.redirect(PAGE);
    }
}
