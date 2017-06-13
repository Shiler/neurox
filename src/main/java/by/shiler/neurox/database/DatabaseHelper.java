package by.shiler.neurox.database;

import by.shiler.neurox.repository.Game;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

/**
 * Created by Evgeny Yushkevich on 13.06.2017.
 */
public class DatabaseHelper {

    private final static Logger LOG = LogManager.getLogger(DatabaseHelper.class);

    private Session session;

    public DatabaseHelper() {
        session = new Configuration().configure().buildSessionFactory().openSession();
        LOG.info("DatabaseHelper initialized.");
    }

    public void saveGame(Game game) {
        session.save(game);
        LOG.info("Game '" + game.getId() + "' saved.");
    }

    public void closeSession() {
        session.close();
    }

}
