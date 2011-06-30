package inventario



import org.junit.*
import grails.test.mixin.*


@TestFor(EntradaController)
@Mock(Entrada)
class EntradaControllerTests {


    @Test
    void testIndex() {
        controller.index()
        assert "/entrada/list" == response.redirectedUrl
    }

    @Test
    void testList() {

        def model = controller.list()

        assert model.entradaInstanceList.size() == 0
        assert model.entradaInstanceTotal == 0

    }

    @Test
    void testCreate() {
       def model = controller.create()

       assert model.entradaInstance != null


    }

    @Test
    void testSave() {
        controller.save()

        assert model.entradaInstance != null
        assert view == '/entrada/create'

        // TODO: Populate valid properties

        controller.save()

        assert response.redirectedUrl == '/entrada/show/1'
        assert controller.flash.message != null
        assert Entrada.count() == 1
    }


    @Test
    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/entrada/list'


        def entrada = new Entrada()

        // TODO: populate domain properties

        assert entrada.save() != null

        params.id = entrada.id

        def model = controller.show()

        assert model.entradaInstance == entrada
    }

    @Test
    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/entrada/list'


        def entrada = new Entrada()

        // TODO: populate valid domain properties

        assert entrada.save() != null

        params.id = entrada.id

        def model = controller.edit()

        assert model.entradaInstance == entrada
    }

    @Test
    void testUpdate() {

        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/entrada/list'

        response.reset()


        def entrada = new Entrada()

        // TODO: populate valid domain properties

        assert entrada.save() != null

        // test invalid parameters in update
        params.id = entrada.id

        controller.update()

        assert view == "/entrada/edit"
        assert model.entradaInstance != null

        entrada.clearErrors()

        // TODO: populate valid domain form parameter
        controller.update()

        assert response.redirectedUrl == "/entrada/show/$entrada.id"
        assert flash.message != null
    }

    @Test
    void testDelete() {
        controller.delete()

        assert flash.message != null
        assert response.redirectedUrl == '/entrada/list'

        response.reset()

        def entrada = new Entrada()

        // TODO: populate valid domain properties
        assert entrada.save() != null
        assert Entrada.count() == 1

        params.id = entrada.id

        controller.delete()

        assert Entrada.count() == 0
        assert Entrada.get(entrada.id) == null
        assert response.redirectedUrl == '/entrada/list'


    }


}