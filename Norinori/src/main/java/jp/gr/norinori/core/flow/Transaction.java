package jp.gr.norinori.core.flow;

/**
 * トランザクション
 *
 * @author nori
 */
public interface Transaction extends LifeCycle {

    /**
     * 処理を確定する
     *
     * @throws Exception
     */
    public void commit() throws Exception;

    /**
     * セーブポイントを設定する.
     *
     * @param savePoint セーブポイント
     * @throws Exception
     */
    public void save(String savePoint) throws Exception;

    /**
     * 処理を開始状態に戻す
     *
     * @throws Exception
     */
    public void rollback() throws Exception;

    /**
     * 処理をセーブポイントに戻す
     *
     * @param savePoint セーブポイント
     * @throws Exception
     */
    public void rollback(String savePoint) throws Exception;
}
