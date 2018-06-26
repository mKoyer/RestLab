import common.Mark;
import common.Student;
import common.Subject;
import dao.IDao;
import dao.MongoDao;
import org.bson.types.ObjectId;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.linking.DeclarativeLinkingFeature;
import org.glassfish.jersey.server.ResourceConfig;
import resources.MarksResource;
import resources.StudentsResource;
import resources.SubjectsResource;
import services.MarksService;
import services.StudentService;

import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {

    public static void main(String[] args) {
        //initialSetup();
        URI baseUri = UriBuilder.fromUri("http://localhost/").port(9998).build();
        ResourceConfig config = new ResourceConfig(StudentsResource.class, SubjectsResource.class, MarksResource.class);
        config.register(DeclarativeLinkingFeature.class);

        Logger l = Logger.getLogger("org.glassfish.grizzly.http.server.HttpHandler");
        l.setLevel(Level.FINE);
        l.setUseParentHandlers(false);
        ConsoleHandler ch = new ConsoleHandler();
        ch.setLevel(Level.ALL);
        l.addHandler(ch);

        HttpServer server = GrizzlyHttpServerFactory.createHttpServer(baseUri, config);
    }

    static void initialSetup() {
        MarksService marksService = MarksService.getInstance();
        IDao dao = MongoDao.getInstance();
        String string = "January 2, 2010";
        Subject math = new Subject(new ObjectId(), "Math Teacher", "Math");
        Subject physic = new Subject(new ObjectId(), "PE Teacher", "PE");
        Subject economy = new Subject(new ObjectId(), "TPSI Teacher", "TPSI");
        math = dao.saveSubject(math);
        physic = dao.saveSubject(physic);
        economy = dao.saveSubject(economy);

        DateFormat format = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
        Date date = null;
        try {
            date = format.parse(string);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Student st1 = new Student("John", "Kowalsky", date);
        dao.saveStudent(st1);

        Student st2 = new Student("Marie", "Smith", date);
        dao.saveStudent(st2);

        Mark g1 = new Mark("3.5", math);
        Mark g2 = new Mark("5", economy);
        marksService.addMark(st1.getIndex(),g1);
        marksService.addMark(st1.getIndex(),g2);

        Mark g3 = new Mark("5", physic);
        Mark g4 = new Mark("4", economy);
        marksService.addMark(st2.getIndex(),g3);
        marksService.addMark(st2.getIndex(),g4);
    }
}