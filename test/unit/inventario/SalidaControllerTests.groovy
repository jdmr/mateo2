package inventario



import org.junit.*
import grails.test.mixin.*


@TestFor(SalidaController)
@Mock(Salida)
class SalidaControllerTests {


    @Test
    void testIndex() {
        controller.index()
        assert "/salida/list" == response.redirectedUrl
    }

    @Test
    void testList() {

        def model = controller.list()

        assert model.salidaInstanceList.size() == 0
        assert model.salidaInstanceTotal == 0

    }

    @Test
    void testCreate() {
       def model = controller.create()

       assert model.salidaInstance != null


    }

    @Test
    void testSave() {
        controller.save()

        assert model.salidaInstance != null
        assert view == '/salida/create'

        // TODO: Populate valid properties

        controller.save()

        assert response.redirectedUrl == '/salida/show/1'
        assert controller.flash.message != null
        assert Salida.count() == 1
    }


    @Test
    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/salida/list'


        def salida = new Salida()

        // TODO: populate domain properties

        assert salida.save() != null

        params.id = salida.id

        def model = controller.show()

        assert model.salidaInstance == salida
    }

    @Test
    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/salida/list'


        def salida = new Salida()

        // TODO: populate valid domain properties

        assert salida.save() != null

        params.id = salida.id

        def model = controller.edit()

        assert model.salidaInstance == salida
    }

    @Test
    void testUpdate() {

        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/salida/list'

        response.reset()


        def salida = new Salida()

        // TODO: populate valid domain properties

        assert salida.save() != null

        // test invalid parameters in update
        params.id = salida.id

        controller.update()

        assert view == "/salida/edit"
        assert model.salidaInstance != null

        salida.clearErrors()

        // TODO: populate valid domain form parameter
        controller.update()

        assert response.redirectedUrl == "/salida/show/$salida.id"
        assert flash.message != null
    }

    @Test
    void testDelete() {
        controller.delete()

        assert flash.message != null
        assert response.redirectedUrl == '/salida/list'

        response.reset()

        def salida = new Salida()

        // TODO: populate valid domain properties
        assert salida.save() != null
        assert Salida.count() == 1

        params.id = salida.id

        controller.delete()

        assert Salida.count() == 0
        assert Salida.get(salida.id) == null
        assert response.redirectedUrl == '/salida/list'


    }


}