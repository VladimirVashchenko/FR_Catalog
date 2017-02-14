package frCatalog;

import frCatalog.model.entities.*;
import javafx.application.Application;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.stat.Statistics;

import java.util.List;

public class Main extends Application {

    private static Stage primaryStage;
    private final DBHelper db = DBHelper.getInstance();

    SessionFactory sessionFactory;

    @Override
    public void start(Stage primaryStage) throws Exception {
        /*this.primaryStage = primaryStage;
        final Controller controller = new Controller();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/mainWindow.fxml"));
        loader.setController(controller);
        Parent root = loader.load();

        Image icon = new Image(getClass().getResourceAsStream("/logo final _ 3.png"));
        primaryStage.getIcons().add(icon);


        primaryStage.setTitle("Catalog");
        primaryStage.setScene(new Scene(root));
        primaryStage.setWidth(1280);
        primaryStage.setHeight(720);
        primaryStage.setMinWidth(500);
        primaryStage.setMinHeight(300);
        primaryStage.show();*/

//        db.connectToDB();
//        db.createTables();

        /*controller.setMain(this);
        controller.setPrimaryStage(this.primaryStage);
        controller.setButtons();*/




        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();






        Author author = new Author("Robert","Antonio", "Salvatore");
        Series series = new Series("Series");
        Novel novel = new Novel("novel", "2005");
        Anthology anthology = new Anthology("anthology");
        NovelAuthor novelAuthor = new NovelAuthor();
        SeriesAuthor seriesAuthor = new SeriesAuthor();
        SeriesNovel seriesNovel = new SeriesNovel();
        AnthologyAuthor anthologyAuthor = new AnthologyAuthor();
        AnthologyNovel anthologyNovel = new AnthologyNovel();
        SingleNovels singleNovel = new SingleNovels();
        ReadNovels readNovel = new ReadNovels();

        seriesAuthor.setSeries(series);
        seriesAuthor.setAuthor(author);

        session.save(author);
        session.save(series);
        session.save(seriesAuthor);

        singleNovel.setNovel(novel);
        novel.setSingleNovels(singleNovel);

        readNovel.setNovel(novel);
        novel.setReadNovels(readNovel);

        novelAuthor.setAuthor(author);
        novelAuthor.setNovel(novel);
        author.getNovelAuthors().add(novelAuthor);
        novel.getNovelAuthors().add(novelAuthor);

        seriesNovel.setSeries(series);
        seriesNovel.setNovel(novel);

        series.getSeriesNovels().add(seriesNovel);
        novel.getSeriesNovels().add(seriesNovel);

//      session.save(singleNovel); // и так, и так можно
        session.save(novel);
        session.save(singleNovel);

	    anthologyAuthor.setAuthor(author);
	    anthologyAuthor.setAnthology(anthology);
	    author.getAnthologyAuthors().add(anthologyAuthor);
	    anthology.setAnthologyAuthor(anthologyAuthor);

	    anthologyNovel.setAnthology(anthology);
	    anthologyNovel.setNovel(novel);
	    anthology.getAnthologyNovels().add(anthologyNovel);
        novel.getAnthologyNovels().add(anthologyNovel);
        session.save(anthology);
        session.getTransaction().commit();

        List result = session.createQuery( "from Author" ).list();
        for ( Author author1 : (List<Author>) result ) {
            System.out.println( "Author: " + author1.getFirstName() + " " + author1.getSecondName() + " " +author1.getSurname());
        }
        Statistics stats = sessionFactory.getStatistics();
        long queryCount = stats.getQueryExecutionCount();
        System.out.println("sql count: "+queryCount);
    }

    public static void main(String[] args) {
        launch(args);
    }
    public Stage getPrimaryStage(){
        return primaryStage;
    }
}