package inventario



import org.junit.*
import grails.test.mixin.*


@TestFor(LoteSalidaController)
@Mock(LoteSalida)
class LoteSalidaControllerTests {


    @Test
    void testIndex() {
        controller.index()
        assert "/loteSalida/list" == response.redirectedUrl
    }

    @Test
    void testList() {

        def model = controller.list()

        assert model.loteSalidaInstanceList.size() == 0
        assert model.loteSalidaInstanceTotal == 0

    }

    @Test
    void testCreate() {
       def model = controller.create()

       assert model.loteSalidaInstance != null


    }

    @Test
    void testSave() {
        controller.save()

        assert model.loteSalidaInstance != null
        assert view == '/loteSalida/create'

        // TODO: Populate valid properties

        controller.save()

        assert response.redirectedUrl == '/loteSalida/show/1'
        assert controller.flash.message != null
        assert LoteSalida.count() == 1
    }


    @Test
    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/loteSalida/list'


        def loteSalida = new LoteSalida()

        // TODO: populate domain properties

        assert loteSalida.save() != null

        params.id = loteSalida.id

        def model = controller.show()

        assert model.loteSalidaInstance == loteSalida
    }

    @Test
    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/loteSalida/list'


        def loteSalida = new LoteSalida()

        // TODO: populate valid domain properties

        assert loteSalida.save() != null

        params.id = loteSalida.id

        def model = controller.edit()

        assert model.loteSalidaInstance == loteSalida
    }

    @Test
    void testUpdate() {

        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/loteSalida/list'

        response.reset()


        def loteSalida = new LoteSalida()

        // TODO: populate valid domain properties

        assert loteSalida.save() != null

        // test invalid parameters in update
        params.id = loteSalida.id

        controller.update()

        assert view == "/loteSalida/edit"
        assert model.loteSalidaInstance != null

        loteSalida.clearErrors()

        // TODO: populate valid domain form parameter
        controller.update()

        assert response.redirectedUrl == "/loteSalida/show/$loteSalida.id"
        assert flash.message != null
    }

    @Test
    void testDelete() {
        controller.delete()

        assert flash.message != null
        assert response.redirectedUrl == '/loteSalida/list'

        response.reset()

        def loteSalida = new LoteSalida()

        // TODO: populate valid domain properties
        assert loteSalida.save() != null
        assert LoteSalida.count() == 1

        params.id = loteSalida.id

        controller.delete()

        assert LoteSalida.count() == 0
        assert LoteSalida.get(loteSalida.id) == null
        assert response.redirectedUrl == '/loteSalida/list'


    }


}