package contabilidad



import org.junit.*
import grails.test.mixin.*


@TestFor(EjercicioController)
@Mock(Ejercicio)
class EjercicioControllerTests {


    @Test
    void testIndex() {
        controller.index()
        assert "/ejercicio/list" == response.redirectedUrl
    }

    @Test
    void testList() {

        def model = controller.list()

        assert model.ejercicioInstanceList.size() == 0
        assert model.ejercicioInstanceTotal == 0

    }

    @Test
    void testCreate() {
       def model = controller.create()

       assert model.ejercicioInstance != null


    }

    @Test
    void testSave() {
        controller.save()

        assert model.ejercicioInstance != null
        assert view == '/ejercicio/create'

        // TODO: Populate valid properties

        controller.save()

        assert response.redirectedUrl == '/ejercicio/show/1'
        assert controller.flash.message != null
        assert Ejercicio.count() == 1
    }


    @Test
    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/ejercicio/list'


        def ejercicio = new Ejercicio()

        // TODO: populate domain properties

        assert ejercicio.save() != null

        params.id = ejercicio.id

        def model = controller.show()

        assert model.ejercicioInstance == ejercicio
    }

    @Test
    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/ejercicio/list'


        def ejercicio = new Ejercicio()

        // TODO: populate valid domain properties

        assert ejercicio.save() != null

        params.id = ejercicio.id

        def model = controller.edit()

        assert model.ejercicioInstance == ejercicio
    }

    @Test
    void testUpdate() {

        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/ejercicio/list'

        response.reset()


        def ejercicio = new Ejercicio()

        // TODO: populate valid domain properties

        assert ejercicio.save() != null

        // test invalid parameters in update
        params.id = ejercicio.id

        controller.update()

        assert view == "/ejercicio/edit"
        assert model.ejercicioInstance != null

        ejercicio.clearErrors()

        // TODO: populate valid domain form parameter
        controller.update()

        assert response.redirectedUrl == "/ejercicio/show/$ejercicio.id"
        assert flash.message != null
    }

    @Test
    void testDelete() {
        controller.delete()

        assert flash.message != null
        assert response.redirectedUrl == '/ejercicio/list'

        response.reset()

        def ejercicio = new Ejercicio()

        // TODO: populate valid domain properties
        assert ejercicio.save() != null
        assert Ejercicio.count() == 1

        params.id = ejercicio.id

        controller.delete()

        assert Ejercicio.count() == 0
        assert Ejercicio.get(ejercicio.id) == null
        assert response.redirectedUrl == '/ejercicio/list'


    }


}