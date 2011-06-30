package general



import org.junit.*
import grails.test.mixin.*


@TestFor(ClienteController)
@Mock(Cliente)
class ClienteControllerTests {


    @Test
    void testIndex() {
        controller.index()
        assert "/cliente/list" == response.redirectedUrl
    }

    @Test
    void testList() {

        def model = controller.list()

        assert model.clienteInstanceList.size() == 0
        assert model.clienteInstanceTotal == 0

    }

    @Test
    void testCreate() {
       def model = controller.create()

       assert model.clienteInstance != null


    }

    @Test
    void testSave() {
        controller.save()

        assert model.clienteInstance != null
        assert view == '/cliente/create'

        // TODO: Populate valid properties

        controller.save()

        assert response.redirectedUrl == '/cliente/show/1'
        assert controller.flash.message != null
        assert Cliente.count() == 1
    }


    @Test
    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/cliente/list'


        def cliente = new Cliente()

        // TODO: populate domain properties

        assert cliente.save() != null

        params.id = cliente.id

        def model = controller.show()

        assert model.clienteInstance == cliente
    }

    @Test
    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/cliente/list'


        def cliente = new Cliente()

        // TODO: populate valid domain properties

        assert cliente.save() != null

        params.id = cliente.id

        def model = controller.edit()

        assert model.clienteInstance == cliente
    }

    @Test
    void testUpdate() {

        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/cliente/list'

        response.reset()


        def cliente = new Cliente()

        // TODO: populate valid domain properties

        assert cliente.save() != null

        // test invalid parameters in update
        params.id = cliente.id

        controller.update()

        assert view == "/cliente/edit"
        assert model.clienteInstance != null

        cliente.clearErrors()

        // TODO: populate valid domain form parameter
        controller.update()

        assert response.redirectedUrl == "/cliente/show/$cliente.id"
        assert flash.message != null
    }

    @Test
    void testDelete() {
        controller.delete()

        assert flash.message != null
        assert response.redirectedUrl == '/cliente/list'

        response.reset()

        def cliente = new Cliente()

        // TODO: populate valid domain properties
        assert cliente.save() != null
        assert Cliente.count() == 1

        params.id = cliente.id

        controller.delete()

        assert Cliente.count() == 0
        assert Cliente.get(cliente.id) == null
        assert response.redirectedUrl == '/cliente/list'


    }


}