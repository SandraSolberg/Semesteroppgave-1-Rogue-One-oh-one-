package inf101.v20.rogue101.game;

/**
 *
 * @author Sandra Solberg - sit006@uib.no
 * Generisk-list som skal holde på Iitems
 */

public interface IPlayerBag<T> {

    /**
     * @param s
     *                Nytt Iitem skal legges til på slutten av listen
     * 	 *
     * 	 *            Etterpå vil size() øke med én, og get(size()-1) vil returnere objektene
     * 	 *
     *
     */
    void add(T s);



    T get(int index);

    /**
     * Sletter Iitems fra listen
     * Etterpå vil alle senere indekser i listen flyttes én posisjon frem.
     *
     */

    T remove(int i);


    /**
     *
     * @return True hvis listen er tom
     *
     *
     */
    boolean isEmpty();

    /**
     * @return Antall størrelsen på listen
     */
    int size();

    /**
     *
     * @param Iitem
     * @return sjekker om et objekt ligger i listen
     */
    boolean contains(T Iitem);



    }

