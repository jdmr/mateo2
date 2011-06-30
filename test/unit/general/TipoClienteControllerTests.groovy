package general



import org.junit.*
import grails.test.mixin.*


@TestFor(TipoClienteController)
@Mock(TipoCliente)
class TipoClienteControllerTests {


    @Test
    void testIndex() {
        controller.index()
        assert "/tipoCliente/list" == response.redirectedUrl
    }

    @Test
    void testList() {

        def model = controller.list()

        assert model.tipoClienteInstanceList.size() == 0
        assert model.tipoClienteInstanceTotal == 0

    }

    @Test
    void testCreate() {
       def model = controller.create()

       assert model.tipoClienteInstance != null


    }

    @Test
    void testSave() {
        controller.save()

        assert model.tipoClienteInstance != null
        assert view == '/tipoCliente/create'

        // TODO: Populate valid properties

        controller.save()

        assert response.redirectedUrl == '/tipoCliente/show/1'
        assert controller.flash.message != null
        assert TipoCliente.count() == 1
    }


    @Test
    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/tipoCliente/list'


        def tipoCliente = new TipoCliente()

        // TODO: populate domain properties

        assert tipoCliente.save() != null

        params.id = tipoCliente.id

        def model = controller.show()

        assert model.tipoClienteInstance == tipoCliente
    }

    @Test
    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/tipoCliente/list'


        def tipoCliente = new TipoCliente()

        // TODO: populate valid domain properties

        assert tipoCliente.save() != null

        params.id = tipoCliente.id

        def model = controller.edit()

        assert model.tipoClienteInstance == tipoCliente
    }

    @Test
    void testUpdate() {

        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/tipoCliente/list'

        response.reset()


        def tipoCliente = new TipoCliente()

        // TODO: populate valid domain properties

        assert tipoCliente.save() != null

        // test invalid parameters in update
        params.id = tipoCliente.id

        controller.update()

        assert view == "/tipoCliente/edit"
        assert model.tipoClienteInstance != null

        tipoCliente.clearErrors()

        // TODO: populate valid domain form parameter
        controller.update()

        assert response.redirectedUrl == "/tipoCliente/show/$tipoCliente.id"
        assert flash.message != null
    }

    @Test
    void testDelete() {
        controller.delete()

        assert flash.message != null
        assert response.redirectedUrl == '/tipoCliente/list'

        response.reset()

        def tipoCliente = new TipoCliente()

        // TODO: populate valid domain properties
        assert tipoCliente.save() != null
        assert TipoCliente.count() == 1

        params.id = tipoCliente.id

        controller.delete()

        assert TipoCliente.count() == 0
        assert TipoCliente.get(tipoCliente.id) == null
        assert response.redirectedUrl == '/tipoCliente/list'


    }


}