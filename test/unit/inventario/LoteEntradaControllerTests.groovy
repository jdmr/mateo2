package inventario



import org.junit.*
import grails.test.mixin.*


@TestFor(LoteEntradaController)
@Mock(LoteEntrada)
class LoteEntradaControllerTests {


    @Test
    void testIndex() {
        controller.index()
        assert "/loteEntrada/list" == response.redirectedUrl
    }

    @Test
    void testList() {

        def model = controller.list()

        assert model.loteEntradaInstanceList.size() == 0
        assert model.loteEntradaInstanceTotal == 0

    }

    @Test
    void testCreate() {
       def model = controller.create()

       assert model.loteEntradaInstance != null


    }

    @Test
    void testSave() {
        controller.save()

        assert model.loteEntradaInstance != null
        assert view == '/loteEntrada/create'

        // TODO: Populate valid properties

        controller.save()

        assert response.redirectedUrl == '/loteEntrada/show/1'
        assert controller.flash.message != null
        assert LoteEntrada.count() == 1
    }


    @Test
    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/loteEntrada/list'


        def loteEntrada = new LoteEntrada()

        // TODO: populate domain properties

        assert loteEntrada.save() != null

        params.id = loteEntrada.id

        def model = controller.show()

        assert model.loteEntradaInstance == loteEntrada
    }

    @Test
    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/loteEntrada/list'


        def loteEntrada = new LoteEntrada()

        // TODO: populate valid domain properties

        assert loteEntrada.save() != null

        params.id = loteEntrada.id

        def model = controller.edit()

        assert model.loteEntradaInstance == loteEntrada
    }

    @Test
    void testUpdate() {

        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/loteEntrada/list'

        response.reset()


        def loteEntrada = new LoteEntrada()

        // TODO: populate valid domain properties

        assert loteEntrada.save() != null

        // test invalid parameters in update
        params.id = loteEntrada.id

        controller.update()

        assert view == "/loteEntrada/edit"
        assert model.loteEntradaInstance != null

        loteEntrada.clearErrors()

        // TODO: populate valid domain form parameter
        controller.update()

        assert response.redirectedUrl == "/loteEntrada/show/$loteEntrada.id"
        assert flash.message != null
    }

    @Test
    void testDelete() {
        controller.delete()

        assert flash.message != null
        assert response.redirectedUrl == '/loteEntrada/list'

        response.reset()

        def loteEntrada = new LoteEntrada()

        // TODO: populate valid domain properties
        assert loteEntrada.save() != null
        assert LoteEntrada.count() == 1

        params.id = loteEntrada.id

        controller.delete()

        assert LoteEntrada.count() == 0
        assert LoteEntrada.get(loteEntrada.id) == null
        assert response.redirectedUrl == '/loteEntrada/list'


    }


}